import { AxiosRequestConfig } from 'axios'
import { axiosPOST } from './base'

export type PostUserJoinRequestType = {
  email: string
	password: string
	nickname: string
	introduce: string
  gitid: string
}

export type PostUserJoinResponseType = {
  status: "SUCCESS" | "FAILED"
	message?: string
  id: string
}

export const postuserJoin = (url: string, data: PostUserJoinRequestType, config?: AxiosRequestConfig) => {
 return axiosPOST<PostUserJoinRequestType, PostUserJoinResponseType>(
    url,
    data,
    config
  )
 }