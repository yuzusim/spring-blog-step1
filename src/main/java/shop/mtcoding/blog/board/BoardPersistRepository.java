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

    // 애초부터 Board 객체를 받는 것
    @Transactional
    public Board save(Board board){
        em.persist(board);
        return board;
    }



    public Board findById(int id){
        Query query =
                em.createNativeQuery("select * from board_tb where id =?", Board.class);
        query.setParameter(1, id);
        return (Board) query.getSingleResult();
    }

    public List<Board> findAll(){
        Query query =
                em.createNativeQuery("select * from board_tb order by id desc", Board.class);
        return (List<Board>) query.getResultList();
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