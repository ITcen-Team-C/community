package store.itcen.community.chat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO CHATROOM(post_id, seller_id, buyer_id, file_name, created_date, post_title) VALUES ( :#{#chatRoom.post_id}, :#{#chatRoom.sellerId}, :#{#chatRoom.buyerId}, :#{#chatRoom.fileName}, :#{#chatRoom.createdDate}, :#{#chatRoom.post_title})", nativeQuery = true)
	public void addChatRoom (@Param("chatRoom") ChatRoom chatRoom) throws IOException;
	
	//String chatId, String pr_id, String senderId, String recipientId

	@Query(value = "SELECT c.* FROM CHATROOM AS c JOIN BOARD as b ON c.post_id = b.id WHERE c.seller_id = :#{#userId} OR c.buyer_id = :#{#userId}", nativeQuery = true)
	public List<ChatRoom> findByUserId(@Param("userId") String userId);

	@Query(value = "SELECT COUNT(*) FROM CHATROOM WHERE post_id = :#{#post_id} AND buyer_id = :#{#buyerId}", nativeQuery = true)
	public int countByChatId(@Param("post_id")String post_id, @Param("buyerId")String buyerId);

	@Query(value = "SELECT * FROM CHATROOM WHERE post_id = :#{#post_id} AND buyer_id = :#{#buyerId}", nativeQuery = true)
	public ChatRoom findByChatId(@Param("post_id")String post_id, @Param("buyerId")String buyerId);


	@Query(value = "SELECT ID FROM CHATROOM WHERE post_id = :#{#post_id} AND buyer_id = :#{#buyerId}", nativeQuery = true)
	public int getId(String post_id, String buyerId);

	@Transactional
	@Modifying
	@Query(value = "UPDATE CHATROOM SET file_name = :#{#fileName} WHERE ID = :#{#id}", nativeQuery = true)
	public void updateFileName(int id, String fileName);

	@Transactional
	@Modifying
	@Query(value = "UPDATE CHATROOM SET chat_read_buy = :#{#chatReadBuy} WHERE ID = :#{#id}", nativeQuery = true)
	public void updateChatReadBuy(int id, int chatReadBuy);

	@Modifying
	@Transactional
	@Query(value = "UPDATE CHATROOM SET chat_read_sell = :#{#chatReadSell} WHERE ID = :#{#id}", nativeQuery = true)
	public void updateChatReadSell(int id, int chatReadSell);

	@Query(value = "SELECT COUNT(id) FROM CHATROOM WHERE (seller_id = :#{#userId} AND chat_read_sell = 0) OR (buyer_id = :#{#userId} AND chat_read_buy = 0)", nativeQuery = true)
	public int getUnreadMessages(String userId);

	@Query(value = "SELECT id FROM CHATROOM WHERE (seller_id = :#{#userId} AND chat_read_sell = 0) OR (buyer_id = :#{#userId} AND chat_read_buy = 0)", nativeQuery = true)
	public List<Integer> getUnreadChatRoom(String userId);

	
}