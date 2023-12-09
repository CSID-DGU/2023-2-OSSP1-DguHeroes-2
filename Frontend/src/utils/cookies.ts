/* eslint-disable no-undef */
export function setCookie(cname: string, cvalue: string, exdays: number) {
  const d = new Date()
  d.setTime(d.getTime() + exdays * 24 * 60 * 60 * 1000)
  const expires = 'expires=' + d.toUTCString()
  document.cookie = cname + '=' + cvalue + ';' + expires + ';path=/'
}

export function getCookie(name: string): string {
  let cookieValue = ''
  if (document.cookie && document.cookie != '') {
    const cookies = document.cookie.split(';')

    for (const originCookie of cookies) {
      const cookie = originCookie == null ? '' : (originCookie + '').replace(/^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g, '')

      if (cookie.substring(0, name.length + 1) == name + '=') {
        cookieValue = decodeURIComponent(cookie.substring(name.length + 1))
        break
      }
    }
  }
  return cookieValue
}

export function deleteCookie(name: string) {
  document.cookie = name + '=; expires=Thu, 01 Jan 1999 00:00:10 GMT;'
}
