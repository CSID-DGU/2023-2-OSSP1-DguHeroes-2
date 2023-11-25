import { DevelopmentStackType } from 'types/project'

export const translateDevelopmentStack = (value: DevelopmentStackType) => {
  if (value === 'FRONTEND') {
    return '프론트엔드'
  }
  if (value === 'BACKEND') {
    return '백엔드'
  }
  if (value === 'ETC') {
    return '기타 개발 스택'
  }
}

export const getDevelopmentStackColor = (value: DevelopmentStackType) => {
  if (value === 'WEB_FRONTEND') {
    return 'volcano'
  }
  if (value === 'APP_CLIENT') {
    return 'gold'
  }
  if (value === 'SERVER_BACKEND') {
    return 'cyan'
  }
  if (value === 'ETC') {
    return 'purple'
  }
}
