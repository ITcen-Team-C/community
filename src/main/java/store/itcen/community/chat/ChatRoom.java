package store.itcen.community.chat;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "CHATROOM")
public class ChatRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	private String post_id;
	@Column
	private String seller_id;
	@Column
	private String buyer_id;
	@Column
	private String file_name;
	@Column
	private Timestamp created_date;
	@Column
	private String post_title;
	@Column
	private int chat_read_buy;
	@Column
	private int chat_read_sell;

	//not in DB
	@Transient
	private String content;
	@Transient
	private String sendTime;
	@Transient
	private String senderId;
	
	
	
	
	
	
	@Override
	public String toString() {
		return "ChatRoom [id=" + id + ", pr_id=" + post_id + ", sellerId=" + seller_id + ", buyerId=" + buyer_id
				+ ", fileName=" + file_name + ", createdDate=" + created_date + ", content=" + content + ", sendTime="
				+ sendTime + ", senderId=" + senderId + ", pr_title=" + post_title + "]";
	}

	 
	public ChatRoom(int id, String post_id, String sellerId, String buyerId, String fileName,
					Timestamp createdDate, String post_title, int chatReadBuy, int chatReadSell) {
		super();
		this.id = id;
		this.post_id = post_id;
		this.seller_id = sellerId;
		this.buyer_id = buyerId;
		this.file_name = fileName;
		this.created_date = createdDate;
		this.post_title = post_title;
		this.chat_read_buy = chatReadBuy;
		this.chat_read_sell = chatReadSell;
	}
	
	
	
	

	public int getChatReadBuy() {
		return chat_read_buy;
	}

	public void setChatReadBuy(int chatReadBuy) {
		this.chat_read_buy = chatReadBuy;
	}

	public int getChatReadSell() {
		return chat_read_sell;
	}

	public void setChatReadSell(int chatReadSell) {
		this.chat_read_sell = chatReadSell;
	}

	public ChatRoom() {
		// TODO Auto-generated constructor stub
	}
	
	public ChatRoom(String content, String sendTime, String senderId) {
		this.content = content;
		this.sendTime = sendTime;
		this.senderId = senderId;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPost_id() {
		return post_id;
	}

	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}

	public String getSellerId() {
		return seller_id;
	}

	public void setSellerId(String sellerId) {
		this.seller_id = sellerId;
	}

	public String getBuyerId() {
		return buyer_id;
	}

	public void setBuyerId(String buyerId) {
		this.buyer_id = buyerId;
	}

	public String getFileName() {
		return file_name;
	}

	public void setFileName(String fileName) {
		this.file_name = fileName;
	}

	public Timestamp getCreatedDate() {
		return created_date;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.created_date = createdDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getPost_title() {
		return post_title;
	}

	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}


	
	
	
	
	
	
	
	
	
}
	
	
