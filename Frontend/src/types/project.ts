export type DevelopmentStackType = 'FRONTEND' | 'BACKEND' | 'ETC'

export type DevelopmentStackItemType = {
  stackId : number
	developmentStack : DevelopmentStackType
	grade : number
}

export type DevelopmentStackListType = DevelopmentStackItemType[]

export type ProjectRequireMemberType = {
  developmentStack: DevelopmentStackType
  positionStacks: string[] | undefined
  number: number | undefined
}

export type ProjectRequireMemberListType = ProjectRequireMemberType[]

export type ProjectType = 'FRONTEND' | 'BACKEND' | 'ETC'

export type ProjectPositionType = 'LEADER' | 'MEMBER' | 'NORMAL' 

export type ProjectValidType = 'VALID' | 'EXPIRED'

export type ProjectItemType = {
  key: number
  title: string
  representativeImg?: any
  projectType: ProjectType
  popularScore: number
  requireMemberList: ProjectRequireMemberListType
  visitedNumber: number
  likeNumber: number
  position: ProjectPositionType
  location: number
	projectStartDate: string
	projectEndDate: string
  valid: ProjectValidType
}

export type ProjectDetailItemType = {
	projectContent: string // 게시글 추가
} & ProjectItemType

export type ProjectListType = ProjectItemType[]

export type ApplyProjectStatusType = 'PENDING' | 'BELONG' | 'REJECT'

export type ApplyProjectItemType = {
  status: ApplyProjectStatusType
} & ProjectItemType

export type ApplyProjectListType = ApplyProjectItemType[]

//밑 두 type이 사용되지 않음 경고가 뜸..
/* type ApplyMemberItemType = {
  userKey: number
  nickname: string
  developmentStackList : DevelopmentStackListType
} */

//type ApplyMemberListType = ApplyMemberItemType[]

// 필요 없으니 지우고 바꾸자 =====
/* export type ManageProjectPositionType = 'LEADER' | 'MEMBER'

type ManageProjectItemType = {
  position: ManageProjectPositionType
} & ProjectItemType

export type ManageProjectListType = ManageProjectItemType[] */

/* type ExpireProjectItemType = {
  expireMemberList: ApplyMemberListType
} & ProjectItemType

export type ExpireProjectListType = ExpireProjectItemType[] */

// ====================

export type UserInfoType = {
  userId: number
  email: string
	nickname: string
	introduce: string
  profile? : any
	developmentStackList : DevelopmentStackListType
}

export type UserInfoListType = UserInfoType[]


