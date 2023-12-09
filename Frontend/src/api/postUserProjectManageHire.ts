import { AxiosRequestConfig } from 'axios'
import { axiosPOST } from './base'

export type PostUserProjectManageHireRequestType = {
  projectKey: number
	userKey: number // 지원한 유저
	state: 'APPROVE' | 'REJECT' // 승인했는지 거절했는지 여부
}

export type PostUserProjectManageHireResponseType = {
  status: "SUCCESS" | "FAILED"
	message?: string
}

export const postUserProjectManageHire = (url: string, data: PostUserProjectManageHireRequestType, config?: AxiosRequestConfig) => {
 return axiosPOST<PostUserProjectManageHireRequestType, PostUserProjectManageHireResponseType>(
    url,
    data,
    config
  )
}