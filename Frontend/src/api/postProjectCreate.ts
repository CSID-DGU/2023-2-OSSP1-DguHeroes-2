import { AxiosRequestConfig } from 'axios'
import { axiosPOST } from './base'
import { DevelopmentStackType, ProjectRequireMemberListType, ProjectType } from 'types/project'

export type PostProjectCreateRequestType = {
  title: string // 프로젝트 제목
	projectType: ProjectType
	requireMemberList: ProjectRequireMemberListType
	leaderDevelopmentStack : DevelopmentStackType // 팀장 스택값!
	location: number // 지역
	projectStartDate: string // 프로젝트 시작일
	projectEndDate: string // 프로젝트 종료일
	projectContent: string // 게시글 내용
}

export type PostProjectCreateResponseType = {
  status: "SUCCESS" | "FAILED"
	message?: string
  projectKey: number
}

export const postprojectCreate = (url: string, data: PostProjectCreateRequestType, config?: AxiosRequestConfig) => {
 return axiosPOST<PostProjectCreateRequestType, PostProjectCreateResponseType>(
    url,
    data,
    config
  )
}