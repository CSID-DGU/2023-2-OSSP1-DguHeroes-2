import { AxiosRequestConfig } from 'axios'
import { axiosPOST } from './base'
import { DevelopmentStackType } from 'types/project'

export type PostUserJoinRequestType = {
  id: string
	password: string
	nickname: string
	introduce: string
	email: string
	developmentStack : DevelopmentStackType
	scorePercent : number
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