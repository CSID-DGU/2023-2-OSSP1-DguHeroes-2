export const generateQueryKey = (obj?: Record<string, any>) => {
  if (!obj) {
    return ''
  }

  return Object.keys(obj)
    .filter((key) => `${key}=${obj[key]}` !== `${key}=`)
    .sort()
    .map((key) => `${key}=${obj[key]}`)
    .join('&')
}
