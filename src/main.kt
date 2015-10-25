package CkConn

import java.io.IOException
import java.net.MalformedURLException
import java.net.SocketTimeoutException
import java.net.URL

private var fin = true
private val second = 4 //timeout
private fun conn(url:URL):String {
	var rs: String
	try {
		val connection = url.openConnection() as java.net.HttpURLConnection
		connection.connectTimeout = 1000 * second
		connection.readTimeout = 1000 * second
		connection.connect()
		val status_code = connection.getResponseCode()
		when (status_code) {
			200 -> rs = "正常"
			400 -> {
				rs = "无法访问"
				fin = false
			}
			500 -> {
				rs = "服务器错误"
				fin = false
			}
			else -> {
				rs = "出现其他错误 错误码是：" + status_code
				fin = false
			}
		}
	}
	catch (e:SocketTimeoutException){
		fin = false
		rs = "访问超时 可能网络连接过慢 请再次检查或手动检查 "+url.toString()
	}
	catch (e:MalformedURLException) {
		fin = false
		rs = "MalformedURLException"
	}
	catch (e:IOException) {
		fin = false
		rs = "无法访问"
	}
	catch (e:Exception) {
		fin = false
		e.printStackTrace()
		rs = "出现其他未知错误 " + e.message
	}
	return " "+rs
}
public fun main(args: Array<String>) {
	println("检查开始...")
	println("当前超时设置为："+second+"秒")
	println()
	try {
		println("学工办" + conn(URL("http://www.xgb.buct.edu.cn")))
		println("学工办（跳转后）" + conn(URL("http://www.xgb.buct.edu.cn/SiteFiles/Inner/page.aspx?s=1")))
		println("毕业就业信息网" + conn(URL("http://www.job.buct.edu.cn")))
		println("共青团委会" + conn(URL("http://www.tw.buct.edu.cn")))
		println("武装部" + conn(URL("http://www.wzb.buct.edu.cn")))
		println("学生资助网" + conn(URL("http://www.zizhu.buct.edu.cn")))
		println("DSSA" + conn(URL("http://dssa.buct.edu.cn")))
		println("学生党员在线" + conn(URL("http://202.4.136.138")))
		println("学生党员在线（跳转后）" + conn(URL("http://202.4.136.138/SiteFiles/Inner/page.aspx?s=1")))
		println("胜古朝阳" + conn(URL("http://i.buct.edu.cn")))
		println("花样年华" + conn(URL("http://www.student.buct.edu.cn")))
	} catch (e:Exception) {
		e.printStackTrace()
	}

	println()
	if (fin === true)
		println("网站检查全部正常")
	else
		println("检查的网站中出现错误，请及时记录并报告")

	println("请继续手动检查东区和北区FTP站情况")

//	System.`in`.read()
	println("检查结束。")
	readLine()
}