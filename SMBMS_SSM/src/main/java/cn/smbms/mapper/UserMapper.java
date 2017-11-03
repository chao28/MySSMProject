/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-12 下午2:15:46
 */
package cn.smbms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;

/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-12 下午2:15:46
 */
public interface UserMapper {

	/**
	 * 根据用户code查询用户，可以用作登录，注册
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-12 下午2:24:21
	 * @param codes
	 * @return
	 */
	User findByUserCode(@Param("code")String codes);
	
	/**
	 * 获取用户列表
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-12 下午5:24:20
	 * @param map
	 * @return
	 */
	List<User> getUserList(Map<String, Object> map);
	
	/**
	 * 获取用户记录数
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-12 下午5:29:41
	 * @param userMap
	 * @return
	 */
	Integer getUserCount(Map<String, Object> userMap);

	/**
	 * 添加用户
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-13 下午5:19:24
	 * @param user
	 * @return
	 */
	Integer addUser(User user);

	/**
	 * 根据用户id查询用户信息
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-14 上午10:44:12
	 * @param id
	 * @return
	 */
	User getUserById(@Param("id")Integer id);

	/**
	 * 删除
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-14 下午3:08:45
	 * @param id
	 * @return
	 */
	Integer deleteDel(@Param("id")Integer id);

	/**
	 * 根据用户id查询用户是否存在
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-14 下午3:39:57
	 * @param id
	 * @return
	 */
	User findById(@Param("id")Integer id);

	/**
	 * 修改
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-14 下午5:23:18
	 * @param user
	 * @return
	 */
	Integer updateUser(User user);

	/**
	 * 修改密码
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-14 下午8:17:32
	 * @param id
	 * @param newPwd
	 * @return
	 */
	Integer updatePwd(Map<String, Object> map);
	
}
