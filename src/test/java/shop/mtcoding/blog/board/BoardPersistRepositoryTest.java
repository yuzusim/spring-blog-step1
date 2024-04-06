package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import(BoardPersistRepository.class)
@DataJpaTest
public class BoardPersistRepositoryTest {
    @Autowired
    private BoardPersistRepository boardPersistRepository;
    @Autowired
    private EntityManager em;


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

//

    //    @Test
//    public void updateById_test() {
//        // given
//        int id = 1;
//        String title = "제목1";
//        String content = "내용1";
//        String username = "bori";
//
//        // when
//        boardNativeRepository.updateById(id, title, content, username);
//
//        // then
//        Board board = boardNativeRepository.findById(id);
//        System.out.println("updateById_test : "+board);
//        assertThat(board.getTitle()).isEqualTo("제목1");
//        assertThat(board.getContent()).isEqualTo("내용1");
//        assertThat(board.getUsername()).isEqualTo("bori");
//
//    }

//    @Test
//    public void deleteById_test() {
//       // given
//        int id = 1;
//
//       // when
//        boardNativeRepository.deleteById(id);
//
//       // then
//        List<Board> boardList = boardNativeRepository.findAll();
//        assertThat(boardList.size()).isEqualTo(3);
//        assertThat(boardList.get(1).getTitle()).isEqualTo("제목3");
//
//    }
//
//    // 게시글 상세보기 테스트
//    @Test
//    public void findById_test() {
//       // given
//        int id = 1;
//
//       // when
//        Board board = boardNativeRepository.findById(id);
//        // System.out.println("findById_test : "+board);
//
//       // then
//        assertThat(board.getTitle()).isEqualTo("제목1");
//        assertThat(board.getContent()).isEqualTo("내용1");
//
//    }
//
//    // 게시글 목록보기 테스트
//    @Test
//    public void findAll_test() {
//       // given
//
//       // when
//        List<Board> boardList = boardNativeRepository.findAll();
//
//       // then
//        System.out.println("findAll_test/size : "+boardList.size());
//        System.out.println("findAll_test/username : "+boardList.get(0).getUsername());
//
//        assertThat(boardList.size()).isEqualTo(4);
//        assertThat(boardList.get(2).getUsername()).isEqualTo("ssar");

}



