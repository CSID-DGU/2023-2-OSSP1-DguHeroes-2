import { AxiosRequestConfig } from 'axios'
import { axiosPOST } from './base'

export type PostUserLogoutRequestType = {}

export type PostUserLogoutResponseType = {
  status: "SUCCESS" | "FAILED"
	message?: string
}

export const postuserLogout = (url: string, config?: AxiosRequestConfig) => {
 return axiosPOST<PostUserLogoutRequestType, PostUserLogoutResponseType>(
    url,
    config
  )
}