package CkConn

import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

private var fin = true
private fun conn(url:URL):String {
	var rs: String = ""
	try {
		val connection = url.openConnection() as java.net.HttpURLConnection
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
				rs = "出现其他错误，错误码是：" + status_code
				fin = false
			}
		}
		return " "+rs
	}
	catch (e:MalformedURLException) {
//		e.printStackTrace()
		return "MalformedURLException"
	}
	catch (e:IOException) {
//		e.printStackTrace()
		return "无法访问"
	}

	return rs
}
public fun main(args: Array<String>) {
	try {
		System.out.println("学工办" + conn(URL("http://www.xgb.buct.edu.cn")))
		System.out.println("学工办（跳转后）" + conn(URL("http://www.xgb.buct.edu.cn/SiteFiles/Inner/page.aspx?s=1")))
		System.out.println("毕业就业信息网" + conn(URL("http://www.job.buct.edu.cn")))
		System.out.println("共青团委会" + conn(URL("http://www.tw.buct.edu.cn")))
		System.out.println("武装部" + conn(URL("http://www.wzb.buct.edu.cn")))
		System.out.println("学生资助网" + conn(URL("http://www.zizhu.buct.edu.cn")))
		System.out.println("DSSA" + conn(URL("http://dssa.buct.edu.cn")))
		System.out.println("学生党员在线" + conn(URL("http://202.4.136.138")))
		System.out.println("学生党员在线（跳转后）" + conn(URL("http://202.4.136.138/SiteFiles/Inner/page.aspx?s=1")))
		System.out.println("胜古朝阳" + conn(URL("http://i.buct.edu.cn")))
		System.out.println("花样年华" + conn(URL("http://www.student.buct.edu.cn")))

		if (fin === true) {
			System.out.println("")
			System.out.println("网站检查全部正常")
		} else {
			System.out.println("检查的网站中出现错误，请及时记录并报告")
		}

		System.out.println("请继续手动检查东区和北区FTP站情况")
	} catch (e:MalformedURLException) {
		e.printStackTrace()
	}
//	System.`in`.read()
	readLine()
}