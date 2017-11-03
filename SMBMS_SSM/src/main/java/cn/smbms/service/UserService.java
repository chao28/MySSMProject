/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-12 下午2:16:32
 */
package cn.smbms.service;


import java.util.Map;

import cn.smbms.pojo.User;
import cn.smbms.util.PageUtil;

/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-12 下午2:16:32
 */
public interface UserService {

	/**
	 * 根据用户code查询用户，可以用作登录，注册
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-12 下午2:24:21
	 * @param codes
	 * @return
	 */
	User findByUserCode(String code);
	
	/**
	 * 获取用户列表
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-12 下午5:24:20
	 * @param map
	 * @return
	 */
	PageUtil getUserList(Map<String, Object> map);

	/**
	 * 添加用户
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-13 下午5:18:16
	 * @param user
	 * @return
	 */
	Integer addUser(User user);

	/**
	 * 根据用户id查询用户信息
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-14 上午10:42:38
	 * @param id
	 * @return
	 */
	User getUserById(Integer id);

	/**
	 * 删除
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-14 下午3:07:49
	 * @param id
	 * @return
	 */
	Integer deleteUser(Integer id);

	/**
	 * 根据用户id查询用户是否存在
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-14 下午3:38:32
	 * @param id
	 * @return
	 */
	User findById(Integer id);

	/**
	 * 修改
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-14 下午5:21:26
	 * @param user
	 * @return
	 */
	Integer updateUser(User user);

	/**
	 * 修改密码
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-14 下午8:16:31
	 * @param id
	 * @param newPwd
	 * @return
	 */
	Integer updatePwd(Map<String, Object> map);
	
}
