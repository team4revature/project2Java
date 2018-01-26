package com.revature.Project2.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.Project2.beans.Board;
import com.revature.Project2.beans.User;
import com.revature.Project2.service.BoardService;
import com.revature.Project2.service.UserService;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController
@CrossOrigin(origins = "*")
public class BoardCtrl {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/board/{id}")
	public Board getBoard(@PathVariable int id) {
		
		return boardService.getBoard(id);
	}
	@GetMapping("/getboards/{id}")
	public List<Board> getBoards(@PathVariable int id) {
		User userd = userService.getUser(id);
		return userd.getBoards();
	}
	
	@PostMapping("/createBoard")
	public ResponseEntity<Board> createBoard(@RequestBody Board board){
		System.out.println("fuckers" + board.getBid());
		
		System.out.println("userid: " + board.getScrumMaster().getUid());
		System.out.println("username: " + userService.getUser(board.getScrumMaster().getUid()).getUsername());
		User u = userService.getUser(board.getScrumMaster().getUid());
		
		userService.getUser(board.getScrumMaster().getUid()).getBoards().add(board);
		board = boardService.createBoard(board);
		return new ResponseEntity<Board>(board, HttpStatus.CREATED);
	}
	
}
