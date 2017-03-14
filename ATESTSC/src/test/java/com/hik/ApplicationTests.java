package com.hik;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hik.domain.User;
import com.hik.domain.UserRepository;
import com.hik.domain.Message;
import com.hik.domain.MessageRepository;
import com.hik.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@Transactional
public class ApplicationTests {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private UserService userService;

	@Test
	@Rollback
	public void test() throws Exception {
		userService.add();
		userService.add("JJJ", 100);
		// 测试findAll, 查询所有记录
		Assert.assertEquals(10, userRepository.findAll().size());
		// 测试findByName, 查询姓名为FFF的User
		Assert.assertEquals(60, userRepository.findByName("FFF").getAge().longValue());
		// 测试findUser, 查询姓名为FFF的User
		Assert.assertEquals(60, userRepository.findUser("FFF").getAge().longValue());
		// 测试findByNameAndAge, 查询姓名为FFF并且年龄为60的User
		Assert.assertEquals("FFF", userRepository.findByNameAndAge("FFF", 60).getName());
		// 测试删除姓名为AAA的User
		userRepository.delete(userRepository.findByName("AAA"));
		// 测试findAll, 查询所有记录, 验证上面的删除是否成功
		Assert.assertEquals(9, userRepository.findAll().size());
	}
	
	@Test
	@Rollback
	public void test2() {
		userRepository.save(new User("aaa", 10));
		userRepository.save(new User("bbb", 20));
		userRepository.save(new User("ccc", 30));
		userRepository.save(new User("ddd", 40));
		userRepository.save(new User("eee", 50));
		Assert.assertEquals(5, userRepository.findAll().size());

		messageRepository.save(new Message("o1", "aaaaaaaaaa"));
		messageRepository.save(new Message("o2", "bbbbbbbbbb"));
		messageRepository.save(new Message("o3", "cccccccccc"));
		Assert.assertEquals(3, messageRepository.findAll().size());
	}
}
