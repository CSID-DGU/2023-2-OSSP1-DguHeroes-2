import { AxiosRequestConfig } from 'axios'
import { axiosPOST } from './base'

export type PostUserLoginRequestType = {
  id: string
	password: string
}

export type PostUserLoginResponseType = {
  status: "SUCCESS" | "FAILED"
	message?: string
}

export const postuserLogin = (url: string, data: PostUserLoginRequestType, config?: AxiosRequestConfig) => {
 return axiosPOST<PostUserLoginRequestType, PostUserLoginResponseType>(
    url,
    data,
    config
  )
}