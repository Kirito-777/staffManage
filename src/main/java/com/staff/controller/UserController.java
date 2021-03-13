package com.staff.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.staff.bean.Msg;
import com.staff.bean.User;
import com.staff.dao.UserMapper;
import com.staff.service.UserService;

@CrossOrigin
@Controller
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserMapper userMapper;

	// 分页查询用户数据
	// RequestParam传入当前页数，默认为 1
	@RequestMapping("/getusers")
	@ResponseBody
	public Msg getUsers(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value="id")String id) {

		// 引入PageHelper插件并调用，传入页码及每页的行数
		PageHelper.startPage(page, 8);
//		System.out.println(id);
		List<User> users = userMapper.selectByExample(id);

		// 使用PageInfo包装查询后的结果，连续显示的页数为 10
		PageInfo pageInfo = new PageInfo(users, 10);
		return Msg.success().add("pageInfo", pageInfo);

	}

	@RequestMapping("/users")
	public void Users() {

	}

	// 用户 *登录* 信息处理
	@RequestMapping("/getlogin")
	@ResponseBody
	public Msg getLogin(@RequestBody User user) {

		System.out.println(user);

		System.out.println(user.toString());
		// 根据 *用户名* 查询数据库中的该名用户信息，存入userExited
		User userExited = userMapper.selectByUsername(user.getUsername());

		if (null == userExited) {
			// 用户若不存在，则重新输入登录信息
			System.out.println(user.getUsername() + "用户不存在");
			return Msg.fail();
		} else {// 若存在该用户，则继续进行密码匹配
				// 比较 从前台接收的密码与 userExited中的密码是否匹配
			int result = user.getPassword().compareTo(userExited.getPassword());
			if (0 == result) {
				System.out.println("登录成功");
//		    		flag=0;
			} else {
				System.out.println("密码错误");
//		    		return "0";
				return Msg.fail();
			}
		}

		return Msg.success().add("User", userExited);

	}

	@RequestMapping("/login")
	public void login() {

	}

	// 判定注册的用户是否存在
	@RequestMapping("/userexist")
	@ResponseBody
	public Msg isexist(@RequestParam(value = "username")String username) {
		
		System.out.println(username);
		long flag = userMapper.isexistByUsername(username);
//		int flag = (int) userService.isexist(username);
		System.out.println(flag);
		if (flag==0) {
			return Msg.fail();
		}
		return Msg.success();
		
	}
	
	// 用户 *注册* 信息处理
	@RequestMapping("/getRegister")
	@ResponseBody
	public String getRegister(@RequestBody User user) {

//		request.setCharacterEncoding("UTF-8");

		// 根据 *用户名* 查询数据库中的该名用户信息，存入userExited
		User userExited = userMapper.selectByUsername(user.getUsername());
		if (null == userExited) {
			// 用户若不存在，则开始添加该用户
			
			System.out.println("进入注册");
			// 判断用户的sex,0---男，1----女
//			String sex = user.getSex().equalsIgnoreCase("0") ? "男" : "女";
//			user.setSex(sex);
			System.out.println(user.toString());

//			String id = (int) (Math.random() * 100000) + "";
			String id = (String) UUID.randomUUID().toString().subSequence(0, 4);
			user.setId(id);
			System.out.println(user.toString());
			userMapper.insertSelective(user);
			System.out.println(user.getUsername() + "注册成功");

		} else {
			System.out.println(user.getUsername() + "用户已被注册，请重新输入");
			return "0";
		}

//		System.out.println(user.toString());
		return "1";

	}

	@RequestMapping("/register")
	public void register() {

	}
	
	// 判定用户的旧密码是否正确
	@RequestMapping("/userpwdexist")
	@ResponseBody
	public Msg isexist2(@RequestParam(value = "username")String username,@RequestParam(value = "oldpwd")String oldpwd) {
		
		System.out.println(oldpwd);
		User user = userMapper.selectByUsername(username);
		if (user.getPassword().equals(oldpwd)) {
			return Msg.fail();
		}
		return Msg.success();		
	}
	
	// 用户修改密码
	@RequestMapping("/updatepwd")
	@ResponseBody
	public Msg updatePwd(@RequestBody User user) {
		
		
		System.out.println("修改密码");
		System.out.println(user.toString());
		
//		try {
//			userMapper.updateByUsername(user);
//			return Msg.success();
//		} catch (Exception e) {
//			// TODO: handle exception
//			return Msg.fail().add("exception", e);
//		}
		User userExited = userMapper.selectByUsername(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(userExited.getPassword());
		if (user.getPassword().equals(userExited.getPassword())) {
			return Msg.fail();
		}
		userMapper.updateByUsername(user);
		return Msg.success();
	}
	
	    // 修改用户信息
		@RequestMapping("/updateinfo")
		@ResponseBody
		public Msg updateInfo(@RequestBody User user) {
			
			
			System.out.println("修改用户信息");
			System.out.println(user.toString());
			User userExited = userMapper.selectByUsername(user.getUsername());
//			System.out.println(user.getPassword());
//			System.out.println(userExited.getPassword());
//			if (user.getPassword().equals(userExited.getPassword())) {
//				return Msg.fail();
//			}
			userMapper.updateById(user);
			return Msg.success();
		}
		
	//删除用户
	@RequestMapping("/deleteuser")
	@ResponseBody
	public Msg deleteuser(@RequestParam(value = "id")String id) {
		System.out.println("删除用户");
		userMapper.deleteByPrimaryKey(id);
		return Msg.success();
		
	}
		
	@RequestMapping("/getuser")
	@ResponseBody
	public Msg getUser(@RequestBody User user) {

		System.out.println(user.getUsername());
		// 根据 *用户名* 查询数据库中的该名用户信息，存入userExited
		User userExited = userMapper.selectByUsername(user.getUsername());
		System.out.println(userExited.toString());
		return Msg.success().add("user", userExited);
	}

}
