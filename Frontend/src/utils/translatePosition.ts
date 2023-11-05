import {ProjectPositionType} from 'types/project'

export const translateProjectPosition = (position: ProjectPositionType) => {
  if (position === 'LEADER') {
    return '관리하기'
  }
  if (position === 'MEMBER') {
    return '프로젝트 나가기'
  }
  if (position === 'NORMAL') {
    return '지원하기'
  }
}