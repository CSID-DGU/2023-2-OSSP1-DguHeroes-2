import { AxiosRequestConfig } from 'axios'
import { axiosPOST } from './base'

export type PostUpadateUrsRequestType = {
  id: string
}

export type PostUpdateUrsResponseType = {
  status: "SUCCESS" | "FAILED"
	message?: string
}

export const postUpdateUrs = (url: string, data: PostUpadateUrsRequestType, config?: AxiosRequestConfig) => {
  return axiosPOST<PostUpadateUrsRequestType, PostUpdateUrsResponseType>(
      url,
      data,
      config
  )
}
