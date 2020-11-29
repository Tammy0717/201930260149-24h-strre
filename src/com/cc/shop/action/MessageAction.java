package com.cc.shop.action;

import com.cc.shop.pojo.Message;
import com.cc.shop.service.MessageService;
import com.cc.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MessageAction extends ActionSupport{
	private MessageService messageService;
	
	private Integer page;
	
	private Integer messageid;
	
	// 管理员去留言管理模块
	public String findAll() {

		PageBean<Message> pageBean = messageService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);

		return "toMessageList";
	}
	
	//管理员删除
	public String delete(){
		
		Message message = messageService.findByMessageId(messageid);
		messageService.deleteMessage(message);
		
		return "deleteSuccess";
	}
	
	
	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}


	public Integer getMessageid() {
		return messageid;
	}


	public void setMessageid(Integer messageid) {
		this.messageid = messageid;
	}

}
