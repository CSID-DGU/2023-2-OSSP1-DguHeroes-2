import { AxiosRequestConfig } from 'axios'
import { axiosGET } from './base'
import { ProjectListType } from 'types/project'

export const baseURL = process.env.REACT_APP_API_URL

export type GetProjectListRequestType = {}

export type GetProjectListResponseType = {
  status: "SUCCESS" | "FAILED"
	message?: string
  projectList: ProjectListType
}

const getQueryPath = `${baseURL}/project/list`

export const getProjectList = (params?: GetProjectListRequestType, config?: AxiosRequestConfig) => {
 return axiosGET<GetProjectListRequestType, GetProjectListResponseType>(
    getQueryPath,
    params,
    config
  )
}