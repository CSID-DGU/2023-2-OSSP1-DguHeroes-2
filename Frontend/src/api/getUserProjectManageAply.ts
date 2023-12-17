import { AxiosRequestConfig } from 'axios'
import { axiosGET } from './base'
import { UserInfoListType } from 'types/project'
import { paramFilter } from 'constants/system/paramFilter'

export const baseURL = process.env.REACT_APP_API_URL

export type GetUserProjectManageAplyRequestType = {
  projectKey: number
}

export type GetUserProjectManageAplyResponseType = {
  status: "SUCCESS" | "FAILED"
	message?: string
  UserInfoListType: UserInfoListType
}

const getQueryPath = (params: GetUserProjectManageAplyRequestType) => {
  const paramsValue = JSON.stringify(params.projectKey).replaceAll(paramFilter, "")
  return `${baseURL}}/user/project/manage/aply/${paramsValue}`
}

export const getUserProjectManageAply = (params: GetUserProjectManageAplyRequestType, config?: AxiosRequestConfig) => {
 return axiosGET<GetUserProjectManageAplyRequestType, GetUserProjectManageAplyResponseType>(
    getQueryPath(params),
    params,
    config
  )
}