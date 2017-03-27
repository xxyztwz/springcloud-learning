package com.hik.web;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hik.domain.User;
import com.hik.exception.BaseException;
import com.hik.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	private final Logger logger = Logger.getLogger(getClass());
	@Autowired
	private DiscoveryClient client;
	@Autowired
	private UserService userService;

	@ApiOperation(value = "新增用户",consumes="application/json",nickname="nickname",notes="notes",tags="v1",produces="application/json")
	@ApiImplicitParams({
	                    @ApiImplicitParam(name = "name", value = "用户名", required = true, paramType = "query"),
	                    @ApiImplicitParam(name = "age", value = "年龄", required = true, paramType = "query")
	})
	@ApiResponses({
		@ApiResponse(code = 301, message = "什么都不是"),
		@ApiResponse(code = 500, message = "逗你玩")
	})
	@RequestMapping(method = RequestMethod.POST)
	public User add(@RequestParam String name, @RequestParam Integer age) {
		ServiceInstance instance = client.getLocalServiceInstance();
		User user = userService.add(name,age);
		logger.info("/add, host:" + instance.getHost() + 
				", service_id:" + instance.getServiceId() + ", name:" + name+ ", age:" + age);
		return user;
	}
	
	@ApiOperation(value = "更新用户",consumes="application/json",notes="notes",tags="v1",produces="application/json")
	@ApiImplicitParams({
	                    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path"),
	                    @ApiImplicitParam(name = "user", value = "用户", required = true, paramType = "body", dataType="User")
	})
	@ApiResponses({
		@ApiResponse(code = 302, message = "什么都不是"),
		@ApiResponse(code = 501, message = "逗你玩")
	})
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public User modify(@PathVariable Long id,@RequestBody User user) {
		logger.info(id+user.getName()+user.getAge());
		return userService.add(user.getName(),user.getAge());
	}
	
	@ApiOperation(value = "用户列表",notes="notes",tags="v1",produces="application/json")
	@RequestMapping(method = RequestMethod.GET)
	public List<User> listAll() throws BaseException{
		List<User> l = userService.getAll();
		if(l.size()>0){
			throw new BaseException();
		}
		return l;
	}
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public User delete(@PathVariable Long id){
		userService.delete(id);
		User user = new User();
		user.setId(id);
		return user;
	}
	
	@ApiOperation(tags="v2", value = "获取所有用户")
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public List<User> getAll(){
		return userService.getAll();
	}
	
	
}