import { AxiosRequestConfig } from 'axios'
import { axiosGET } from './base'
import { UserInfoType } from 'types/project'

export type GetUserInfoRequestType = {}

export const baseURL = process.env.REACT_APP_API_URL

export type GetUserInfoResponseType = {
  status: "SUCCESS" | "FAILED"
  message?: string
  userInfo: UserInfoType
  score: number
}

const getQueryPath = `${baseURL}/user/info`

export const getUserInfo = (params?: GetUserInfoRequestType, config?: AxiosRequestConfig) => {
  return axiosGET<GetUserInfoRequestType, GetUserInfoResponseType>(
    getQueryPath,
    params,
    config
  )
}