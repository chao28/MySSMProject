/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-12 下午2:17:11
 */
package cn.smbms.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.smbms.util.PageUtil;
import cn.smbms.mapper.UserMapper;
import cn.smbms.pojo.User;
import cn.smbms.service.UserService;

/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-12 下午2:17:11
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	/**
	 * 根据用户code查询用户，可以用作登录，注册
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-12 下午2:26:25
	 * @param codes
	 * @return
	 */
	@Override
	public User findByUserCode(String code) {
		return userMapper.findByUserCode(code);
	}

	/**
	 * 获取用户列表
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-12 下午5:26:56
	 * @param map
	 * @return
	 */
	@Override
	public PageUtil getUserList(Map<String, Object> map) {

		//分页工具对象
		PageUtil userPage = new PageUtil();
		
		userPage.setCount(userMapper.getUserCount(map)); 
		userPage.setUserList(userMapper.getUserList(map));

		return userPage;
	}

	/**
	 * 添加用户
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-13 下午5:18:49
	 * @param user
	 * @return
	 */
	@Override
	public Integer addUser(User user) {
		
		return userMapper.addUser(user);
	}

	/**
	 * 根据用户id查询用户信息
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-14 上午10:43:13
	 * @param id
	 * @return
	 */
	@Override
	public User getUserById(Integer id) {
		
		return userMapper.getUserById(id);
	}

	/**
	 * 删除
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-14 下午3:08:11
	 * @param id
	 * @return
	 */
	@Override
	public Integer deleteUser(Integer id) {
		
		return userMapper.deleteDel(id);
	}

	/**
	 * 根据用户id查询用户是否存在
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-14 下午3:39:05
	 * @param id
	 * @return
	 */
	@Override
	public User findById(Integer id) {
		
		return userMapper.findById(id);
	}

	/**
	 * 修改
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-14 下午5:22:40
	 * @param user
	 * @return
	 */
	@Override
	public Integer updateUser(User user) {
		
		return userMapper.updateUser(user);
	}

	/**
	 * 修改密码
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-14 下午8:16:56
	 * @param id
	 * @param newPwd
	 * @return
	 */
	@Override
	public Integer updatePwd(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userMapper.updatePwd(map);
	}

}
