package com.cc.shop.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import com.cc.shop.dao.CartDao;
import com.cc.shop.pojo.Cart;
import com.cc.shop.pojo.Product;
import com.cc.shop.pojo.ShopCartItem;
import com.cc.shop.pojo.User;
import com.cc.shop.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 购物车Action
 * 
 * 
 */
public class CartAction extends ActionSupport {
	// 接收pid
	private Integer pid;
	// 接收数量count
	private Integer count;
	// 注入商品的Service
	private ProductService productService;
	
	private CartDao cartDao;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	
	
	public String addCart() {
		ShopCartItem shopCartItem =new ShopCartItem();
		// 设置数量:
		shopCartItem.setPcount(count);
		System.out.println("count:"+count);
		//设置商品id
		shopCartItem.setPid(pid);
		// 根据pid进行查询商品:
		Product product = productService.findByPid(pid);
		// 设置商品:
		shopCartItem.setPrice(product.getShop_price());
		shopCartItem.setImage(product.getImage());
		shopCartItem.setPtotal(count*product.getShop_price());
		shopCartItem.setPname(product.getPname());
		System.out.println("price:"+product.getShop_price());
		User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(user==null)
		{
			System.out.println("你还未登陆，请登录");
			this.addActionMessage("你还未登陆，请登录!");
			return "msg";
		}
		shopCartItem.setUid(user.getUid());
		ShopCartItem cartresult = cartDao.findCartBYUidPid(user.getUid(), pid);
		if(cartresult == null){
			System.out.println("购物车中没有该商品，需要新增");
			cartDao.save(shopCartItem);
		}else {
			shopCartItem.setCartitemid(cartresult.getCartitemid());
			shopCartItem.setPcount(count+cartresult.getCartitemid());
			shopCartItem.setPtotal(shopCartItem.getPcount()*product.getShop_price());
			cartDao.update(shopCartItem);
		} 
		
		return findMyCart();
	}

	// 清空购物车的执行的方法:
	public String clearCart(){
		System.out.println("开始清空购物车");
		User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		System.out.println("userId:"+user.getUid());
		List<ShopCartItem>  mycart= cartDao.findMyCart(user.getUid());
		
		if(mycart!=null){
			System.out.println("mycart的大小:"+mycart.size());
			for(int i=0;i<mycart.size();i++){
				if(mycart.get(i)!=null){
					ShopCartItem shopCartItem = mycart.get(i);
					System.out.println("商品name："+shopCartItem.getPname());
					cartDao.deleteShopCartItem(shopCartItem);
				}
			}
		}
		//ServletActionContext.getRequest().getSession().removeAttribute("cart");
		return findMyCart();
	}
	
	//删除购物车中的某个商品
	public String removeCart(){
		// 当前商品的id
		System.out.println("pid:"+pid);
		//当前登录的用户
		User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		System.out.println("uid:"+user.getUid());
		
		ShopCartItem shopCartItem = cartDao.findCartBYUidPid(user.getUid(), pid);
		if(shopCartItem!=null){
			cartDao.deleteShopCartItem(shopCartItem);
			System.out.println("删除成功");
		}
		if(shopCartItem==null){
			System.out.println("是空的");
		}
		
		// 返到购物车页面:
		return findMyCart();
	}
	
	
	//根据当前登录的用户进行查找购物车
	public String findMyCart(){
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		// 获得用户的id
		Integer uid = existUser.getUid();
		System.out.println("uid:"+existUser.getUid());
		
		List<ShopCartItem>  mycart= cartDao.findMyCart(uid);
		double total = 0.0;
		if(mycart == null)
		{
			System.out.println("购物车中是空的");
			Cart cart = getCart();
			cart.setList(mycart);
		}else
		{
			for(int i =0;i<mycart.size();i++){
				double price = mycart.get(i).getPrice();
				System.out.println("price"+price);
				Integer pcount = mycart.get(i).getPcount();
				System.out.println("pcount:"+pcount);
				double p = price*pcount;
				total = total+p;
				System.out.println("每个商品总价"+p);
			}
			System.out.println("total:"+total);
			Cart cart = getCart();
			cart.setTotal(total);
			cart.setList(mycart);
		}
		
		return "myCart";
	}
	
	
	/**
	 * 获得购物车的方法:从session中获得购物车.
	 * @return
	 */
	private Cart getCart() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			ServletActionContext.getRequest().getSession()
					.setAttribute("cart", cart);
		}
		return cart;
	}

	public CartDao getCartDao() {
		return cartDao;
	}

	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}
}
