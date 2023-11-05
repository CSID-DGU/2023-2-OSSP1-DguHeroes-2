import { isArray, isObject, snakeCase, transform } from 'lodash'

export const decamelizeKey = (obj: Record<string, any>) =>
  transform(obj, (acc: Record<string, any>, value, key, target) => {
    const snakeKey = isArray(target) ? key : snakeCase(key)

    if (typeof File !== 'undefined') {
      acc[snakeKey] = value
    } else if (isObject(value)) {
      acc[snakeKey] = decamelizeKey(value)
    } else {
      acc[snakeKey] = value
    }
  })
