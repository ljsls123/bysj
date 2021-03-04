//salt
var c_passsword_salt="0l5j1s6"
// 获取url参数
// 密码加密
function c_password_md5(pass) {
// 密码加密
	var salt = c_passsword_salt;
	var str = ""+salt.charAt(1)+salt.charAt(3)+salt.charAt(5)+pass+salt.charAt(0)+salt.charAt(4);
	var password = md5(str);
	return password;
}
