import requests
import json
import time

import smtplib
from email.mime.text import MIMEText
from email.header import Header
from email.utils import formataddr


def mail(subject, content, to):
    ret=True
    try:
        msg=MIMEText(content,'plain','utf-8')
        msg['From']=formataddr(["Vaskka",'1139851358@qq.com'])
        msg['To']=formataddr(["Vaskka",to])        
        msg['Subject']=subject 
 
        server=smtplib.SMTP_SSL("smtp.qq.com", 465)
        server.login('1139851358@qq.com', 'tfbzouaytwscfjfg')  
        server.sendmail('1139851358@qq.com',[to, ],msg.as_string())
        server.quit()  # quit
    except Exception:
        ret=False
    return ret
 

ua = {
	"User-Agnet": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36"
}



url = "http://trains.ctrip.com/TrainBooking/Ajax/SearchListHandler.ashx?Action=getSearchList"

data = {
	"value": "{\"IsBus\":false,\"Filter\":\"0\",\"Catalog\":\"\",\"IsGaoTie\":false,\"IsDongChe\":false,\"CatalogName\":\"\",\"DepartureCity\":\"chengdu\",\"ArrivalCity\":\"beijing\",\"HubCity\":\"\",\"DepartureCityName\":\"成都\",\"ArrivalCityName\":\"北京\",\"DepartureDate\":\"2018-12-23\",\"DepartureDateReturn\":\"2018-12-25\",\"ArrivalDate\":\"\",\"TrainNumber\":\"\"}"
}




def main():
	se = requests.session()
	try:
		while True:
			se.get(url="http://trains.ctrip.com/TrainBooking/Search.aspx?from=chengdu&to=beijing&day=39&fromCn=%B3%C9%B6%BC&toCn=%B1%B1%BE%A9", headers=ua)
			resp = se.post(url=url, headers=ua, data=data)

			obj = json.loads(resp.text)

			li = obj["TrainItemsList"]


			for item in li:
				if item["TrainName"] == "G574" or item["TrainName"] == "G308" or item["TrainName"] == "G90":
					if float(item["SeatBookingItem"][0]["Price"]) > 800.0:
						# email

						content = "当前价位:\n%s:%s" % (item["TrainName"], str(item["SeatBookingItem"][0]["Price"]))
						mail("买票提醒", content, "1139851358@qq.com")
					pass
			pass

			time.sleep(5)
			pass
	except Exception as e:
		mail("bug", resp(e), "1139851358@qq.com")
	pass


if __name__ == '__main__':
	main()
	pass

