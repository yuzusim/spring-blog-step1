package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardPersistRepository {
    private final EntityManager em;




    // 게시글 상세보기
//    em.find ?
//    DB한테 요청하는게 아니라, PC한테 1번 PK키를 가지고 있는 오브젝트가 있는지 BR이 물어보는 것!
//    (nativeQuery는 그냥 던지는 것. 복잡한 쿼리는 nativeQuery로 던져야함!)
    public Board findById(int id){
        // em.find(Board.class, id)를 찾으면, Board 로 바꾼다.
        // em.find는 Persistence Context (PC)에서 찾는다는 것!
        Board board =
                em.find(Board.class , id);
        return board;
    }

    // 게시글 목록보기
    public List<Board> findAll(){
        Query query =
                em.createQuery("select b from Board b order by b.id desc", Board.class);
        return query.getResultList();
    }

//    findAll 메소드에서는 여러 개의 결과를 반환해야 하기 때문에 EntityManager의 find 메소드를 사용할 수 없다.
//    find 메소드는 단일 엔티티 객체를 조회할 때 사용되며, 주로 특정 ID를 가진 엔티티를 찾을 때 사용된다.

    // 게시글 쓰기 완료
    // 애초부터 Board 객체를 받는 것
    @Transactional
    public Board save(Board board){
        em.persist(board);
        return board;
    }



    @Transactional
    public void deleteById(int id){
        Query query =
                em.createNativeQuery("delete from board_tb where id =?");
        query.setParameter(1, id);
        query.executeUpdate();
    }


    @Transactional
    public void updateById(int id, String title, String content, String username){
        Query query =
                em.createNativeQuery("update board_tb set title=?, content=?, username=? where id=?");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, username);
        query.setParameter(4, id);

        query.executeUpdate();
    }

}
