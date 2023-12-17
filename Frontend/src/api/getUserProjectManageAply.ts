import { AxiosRequestConfig } from 'axios'
import { axiosGET } from './base'
import { UserInfoListType } from 'types/project'
import { paramFilter } from 'constants/system/paramFilter'

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
  return `/user/project/manage/aply/${paramsValue}`
}

export const getUserProjectManageAply = (params: GetUserProjectManageAplyRequestType, config?: AxiosRequestConfig) => {
 return axiosGET<GetUserProjectManageAplyRequestType, GetUserProjectManageAplyResponseType>(
    getQueryPath(params),
    params,
    config
  )
}