import { AxiosRequestConfig } from 'axios'
import { axiosGET } from './base'
import { UserInfoListType } from 'types/project'
import { paramFilter } from 'constants/system/paramFilter'


export type GetUserProjectManageRecommendRequestType = {
  projectKey: number
}

export type GetUserProjectManageRecommendResponseType = {
  UserInfoListType: UserInfoListType
  status: "SUCCESS" | "FAILED"
	message?: string
}

const getQueryPath = (params: GetUserProjectManageRecommendRequestType) => {
  const paramsValue = JSON.stringify(params.projectKey).replaceAll(paramFilter, "")
  return `/user/project/manage/recommend/${paramsValue}`
}

export const getUserProjectManageRecommend = (params: GetUserProjectManageRecommendRequestType, config?: AxiosRequestConfig) => {
 return axiosGET<GetUserProjectManageRecommendRequestType, GetUserProjectManageRecommendResponseType>(
    getQueryPath(params),
    params,
    config
  )
}