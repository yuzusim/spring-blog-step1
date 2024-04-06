package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardPersistRepository {
    private final EntityManager em;

    // 게시글 삭제하기 완료

    // id 바인딩을 ?로 하지 않아도 됨 JPQL
    // :id는 JPQL(Java Persistence Query Language) 쿼리에서 사용되는 이름 기반 매개변수.
    // 이 매개변수는 쿼리를 실행할 때 동적으로 값을 바인딩할 수 있게 해준다.
    // (여기선 setParameter id 값을 바인딩하는 듯!)
    @Transactional
    public void deleteById(int id){
        Query query =
                em.createQuery("delete from Board b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }


    // 이거 안 쓸 거다.
    @Transactional
    public void deleteByIdV2(int id) {
        Board board = findById(id);

        // 근데 remove가 어떻게 동작하는지 궁금하니 테스트 해보자!
        em.remove(board);
    }



    // 게시글 상세보기 완료
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

    // 게시글 목록보기 완료
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
