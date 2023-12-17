import { AxiosRequestConfig } from 'axios'
import { axiosPOST } from './base'

export type PostUserJoinRequestType = {
  email: string
	password: string
	nickname: string
	introduce: string
  gitId: string
}

export type PostUserJoinResponseType = {
  status: "SUCCESS" | "FAILED"
	message?: string
}

export const postuserJoin = (url: string, data: PostUserJoinRequestType, config?: AxiosRequestConfig) => {
 return axiosPOST<PostUserJoinRequestType, PostUserJoinResponseType>(
    url,
    data,
    config
  )
}