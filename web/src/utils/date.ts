export default function getFormatDate(date: Date) {
  let year = date.getFullYear(); //yyyy
  let month = 1 + date.getMonth(); //M
  let formatMonth = month >= 10 ? month : '0' + month; //month 두자리로 저장
  let day = date.getDate(); //d
  let formatDay = day >= 10 ? day : '0' + day; //day 두자리로 저장
  return year + '-' + formatMonth + '-' + formatDay; //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
}
