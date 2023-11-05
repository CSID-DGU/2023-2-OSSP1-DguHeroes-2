import { ApplyProjectStatusType, ProjectPositionType } from 'types/project'

export const translateApplyProjectStatus = (status: ApplyProjectStatusType) => {
  if (status === 'PENDING') {
    return '대기'
  }
  if (status === 'BELONG') {
    return '수락'
  }
  if (status === 'REJECT') {
    return '거절'
  }
}

export const getApplyProjectStatusColor = (status: ApplyProjectStatusType) => {
  if (status === 'PENDING') {
    return '#87d068'
  }
  if (status === 'BELONG') {
    return '#2db7f5'
  }
  if (status === 'REJECT') {
    return '#f50'
  }
}

export const translateProjectPosition = (position: ProjectPositionType) => {
  if (position === 'LEADER') {
    return '팀장'
  }
  if (position === 'MEMBER') {
    return '팀원'
  }
}

export const getProjectPositionColor = (position: ProjectPositionType) => {
  if (position === 'LEADER') {
    return '#FDD15A'
  }
  if (position === 'MEMBER') {
    return '#6979DB'
  }
}