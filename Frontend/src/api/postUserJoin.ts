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
  data: {
    id: bigint,
    nickname: string,
    password: string,
    profile: string,
    introduce: string,
    email: string,
    stacks: string,
    githubId: string,
    invitations: any,
    project_members: any,
    project_likes: any,
    applys: any
  }
}

export const postuserJoin = (url: string, data: PostUserJoinRequestType, config?: AxiosRequestConfig) => {
 return axiosPOST<PostUserJoinRequestType, PostUserJoinResponseType>(
    url,
    data,
    config
  )
}