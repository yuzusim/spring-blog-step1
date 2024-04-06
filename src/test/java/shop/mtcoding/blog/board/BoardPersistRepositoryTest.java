package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import(BoardPersistRepository.class)
@DataJpaTest    // DataJpaTest에서 @Transactional를 가지고 있음.
public class BoardPersistRepositoryTest {
    @Autowired
    private BoardPersistRepository boardPersistRepository;
    @Autowired
    private EntityManager em;



        @Test
    public void updateById_test() {
        // given
        int id = 1;
        String title = "제목1";
//        String content = "내용1";
//        String username = "bori";

        // when
       // boardPersistRepository.updateById(id, title, content, username);

        // then
        Board board = boardPersistRepository.findById(id);
        board.setTitle(title);
        em.flush();
//        System.out.println("updateById_test : "+board);
//        assertThat(board.getTitle()).isEqualTo("제목1");
//        assertThat(board.getContent()).isEqualTo("내용1");
//        assertThat(board.getUsername()).isEqualTo("bori");

    }

    @Test
    public void deleteById_test() {
        // given
        int id = 1 ;

        // when
        boardPersistRepository.deleteById(id);

        // then

    }

    @Test
    public void deleteByIdV2_test() {
        // given
        int id = 1;

        // when
        boardPersistRepository.deleteByIdV2(id);

        // 조회만 하고 왜 delete를 안 하지? → 트랜젝션 때문! (delete의 특징)
//        delete라는 애는 트랜젝션이 종료될 때 쿼리가 날아가는 특징이 있다.
//        deleteByIdV2를 호출했을 때, 트랜젝션이 종료되는 시점에서 쿼리가 날아간다.
//        왜 지금 안날라가? 자식 트랜젝션이어서 !! (트랜젝션이 2개 걸려있음)
//        단위 테스트를 할 때에는 deleteByIdV2_test 라는게 실행될 때, 트랜젝션이 발동한다.
//        @DataJpaTest는 메타 어노테이션으로 @Transactional을 들고 있다.
//        @Transactional의 기본 -> commit, rollback, 원자성
//        (원자성 - 전부 다 실행되면 commit 하나라도 안되면 rollback)
//        만약 찾지 못하고 예외가 발생하면 자동으로 rollback 해줌.
//        (내가 이런 식으로 트랜젝션 코드를 적지 않아도!)

        // 이 라인 쿼리. 트랜젝션 종료되지 않았지만 강제로 날려보냄
        em.flush();

        Board board = boardPersistRepository.findById(id);
        System.out.println("findById_test : "+board);
        // 왜 null로 뜰까? DB에는 삭제되지 않았는데
        // PC가 똑똑해가지구실제 DB에는 삭제되지 않았는데 알아서 null값 (삭제 되었어요~) 하고
        // 커밋되기 전 데이터를 읽고 있다

        // then

    }

    // 게시글 상세보기
    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
        // 게시글 한건 조회
        Board board = boardPersistRepository.findById(id);
        em.clear(); // em.clear를 사용해서 PC를 비웠기 때문에 쿼리가 2번 날아간다. -> 사용할 필요 없음!
        // 동일한 id를 조회 -> 캐싱이 되었기 때문에 1번 달아가는 쿼리!
        boardPersistRepository.findById(id);

        // then
        assertThat(board.getTitle()).isEqualTo("제목1");
        assertThat(board.getContent()).isEqualTo("내용1");

    }


    // 게시글 목록보기
    @Test
    public void findAll_test() {
        // given

        // when
        List<Board> boardList = boardPersistRepository.findAll();

        // then
        System.out.println("findAll_test/size : " + boardList.size());
        System.out.println("findAll_test/username : " + boardList.get(2).getUsername());

        // org.assertj.core.api
        Assertions.assertThat(boardList.size()).isEqualTo(4);

    }


    // insert test
    // em.persist(board); 는 검증 테스트 할 필요 없지만 처음 써보는 거니까 해봄
    @Test
    public void save_test() {

        // test1
        // given
//        String title = "제목5";
//        String content = "내용5";
//        String username = "bori";

        // when
        // System.out.println("title : "+title);
        // System.out.println("content : "+content);
        // System.out.println("username : "+username);

        // then


        // test2
        // 영속화 후 테스트 코드수정 → 깔끔 자체!
        Board board = new Board("제목5", "내용5", "ssar");

        boardPersistRepository.save(board);
        System.out.println("save_test : " + board);

    }




}



