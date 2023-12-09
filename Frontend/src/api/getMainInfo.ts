import { AxiosRequestConfig } from 'axios'
import { axiosGET } from './base'
import { ProjectListType } from 'types/project'

export type GetMainInfoRequestType = {}

export type GetMainInfoResponseType = {
  status: "SUCCESS" | "FAILED"
	message?: string
  recommendedProjectList: ProjectListType // 로그인이 되어있을 때만 보내주기
	popularProjectList: ProjectListType
	recentProjectList: ProjectListType
}

const getQueryPath = `/main/info`

export const getmainInfo = (params?: GetMainInfoRequestType, config?: AxiosRequestConfig) => {
 return axiosGET<GetMainInfoRequestType, GetMainInfoResponseType>(
    getQueryPath,
    params,
    config
  )
}

