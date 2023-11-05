import axios, { AxiosError, AxiosRequestConfig, AxiosResponse } from 'axios'
import qs from 'qs'
import { camelizeKey } from 'utils/camelizeKey'
import { getCookie } from 'utils/cookies'
import { decamelizeKey } from 'utils/decamelizeKey'

// eslint-disable-next-line no-undef
export const baseURL = process.env.REACT_APP_API_URL

const axiosInstance = axios.create({
  baseURL,
  headers: {
    'Content-Type': 'application/json',
  },
  withCredentials: true,
})

axiosInstance.interceptors.request.use((config: AxiosRequestConfig) => {
  const newConfig = { ...config }

  if (newConfig.params) {
    newConfig.params = decamelizeKey(newConfig.params)
  }
  if (newConfig.data) {
    newConfig.data = decamelizeKey(newConfig.data)
  }

  return newConfig
})

axiosInstance.interceptors.response.use(
  (response: AxiosResponse) => {
    if (response.data && response.headers['content-type'].includes('application/json')) {
      response.data = camelizeKey(response.data)
    }
    return response
  },
  (error: AxiosError) => {
    return Promise.reject(error.response?.data)
  }
)

axiosInstance.defaults.paramsSerializer = (params: any) => qs.stringify(params, { arrayFormat: 'repeat' })

// GET - params에 담음
export const axiosGET = <RequestData, ResponseData>(
  url: string,
  params?: RequestData,
  options?: AxiosRequestConfig
) => {
  // let paramsValue = undefined
  // if(params !== undefined) {
  //   paramsValue = JSON.stringify(params).replaceAll("/", "")
  // }
  // const paramsValue = JSON.stringify(params).replaceAll("/", "")
  //console.log("params", params)
  return axiosInstance
    .get<ResponseData, AxiosResponse<ResponseData>, RequestData>(url, { ...options })
    .then((response) => response.data)
}

// POST - data에 담음
export const axiosPOST = <RequestData, ResponseData>(
  url: string, 
  data?: RequestData, 
  options?: AxiosRequestConfig
) => {
  return axiosInstance
    .post<ResponseData, AxiosResponse<ResponseData>, { csrfmiddlewaretoken: string }>(
      url,
      { ...data, csrfmiddlewaretoken: getCookie('csrftoken') },
      {
        headers: {
          'X-CSRFToken': getCookie('csrftoken'),
        },
        ...options,
      }
    )
    .then((response) => {
      return response.data
    })
}

// Form 태그로 파일, 이미지 전송할 때 사용
export const axiosFormPOST = <RequestData, ResponseData>(
  url: string,
  data?: RequestData,
  options?: AxiosRequestConfig
) => {
  return axiosInstance
    .postForm<ResponseData, AxiosResponse<ResponseData>>(url, data, {
      headers: {
        'X-CSRFToken': getCookie('csrftoken'),
      },
      ...options,
    })
    .then((response) => {
      return response.data
    })
}

export const axiosPATCH = <RequestData, ResponseData>(
  url: string,
  data?: RequestData,
  options?: AxiosRequestConfig
) => {
  return axiosInstance
    .patch<ResponseData, AxiosResponse<ResponseData>, { csrfmiddlewaretoken: string }>(
      url,
      { ...data, csrfmiddlewaretoken: getCookie('csrftoken') },
      {
        headers: {
          'X-CSRFToken': getCookie('csrftoken'),
        },
        ...options,
      }
    )
    .then((response) => response.data)
}

// DELETE - params에 담음
export const axiosDELETE = <RequestData, ResponseData>(
  url: string,
  params?: RequestData,
  options?: AxiosRequestConfig
) => {
  const paramsValue = JSON.stringify(params).replaceAll("/", "")

  return axiosInstance
    .get<ResponseData, AxiosResponse<ResponseData>, RequestData>(url, { params: paramsValue, ...options })
    .then((response) => response.data)
}