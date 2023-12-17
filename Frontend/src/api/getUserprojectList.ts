import { AxiosRequestConfig } from 'axios'
import { axiosGET } from './base'
import { ApplyProjectListType, ProjectListType } from 'types/project'

export type GetUserprojectListRequestType = {}

export type GetUserprojectListResponseType = {
  status: "SUCCESS" | "FAILED"
	message?: string
  BelongProjectList: ProjectListType // 유저가 참여중인 프로젝트
	PendingProjectList: ApplyProjectListType  // 내가 지원한 프로젝트
	likeProjectList: ProjectListType // 내가 좋아요한 프로젝트
	endProjectList: ProjectListType // 마감된 프로젝트
}

const getQueryPath = `/user/project/list`

export const getUserprojectList = (params?: GetUserprojectListRequestType,config?: AxiosRequestConfig) => {
 return axiosGET<GetUserprojectListRequestType, GetUserprojectListResponseType>(
    getQueryPath,
    params,
    config
  )
}