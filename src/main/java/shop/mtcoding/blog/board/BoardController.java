package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardPersistRepository boardNativeRepository;
    private final BoardPersistRepository boardPersistRepository;

    // 게시글 목록보기 완료
    @GetMapping("/")
    public String index(HttpServletRequest request) {
        List<Board> boardList = boardPersistRepository.findAll();
        request.setAttribute("boardList", boardList);
        return "index";
    }

    // 게시글 쓰기 완료
    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO reqDTO) {
        boardPersistRepository.save(reqDTO.toEntity());
        return "redirect:/";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }




    @GetMapping("/board/{id}/update-form")
    public String uodateForm(@PathVariable Integer id, HttpServletRequest request) {
        Board board = boardNativeRepository.findById(id);
        request.setAttribute("board", board);
        return "/board/update-form";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id, String title, String content, String username) {
        boardNativeRepository.updateById(id, title, content, username);
        return "redirect:/board"+id;
    }

    // 게시글 삭제
    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id) {
        boardNativeRepository.deleteById(id);
        return "redirect:/";
    }






    // 게시글 상세보기
    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest request) {
        Board board = boardNativeRepository.findById(id);
        request.setAttribute("board", board);
        return "board/detail";
    }
}
