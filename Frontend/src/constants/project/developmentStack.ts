import { DevelopmentStackType } from 'types/project'

export const defaultDevelopmentStack: DevelopmentStackType[] = ['WEB_FRONTEND', 'SERVER_BACKEND', 'APP_CLIENT', 'ETC']
export const defaultDevelopmentStackWithKey: {
  value: DevelopmentStackType
  key: number
}[] = [
  {
    value: 'WEB_FRONTEND',
    key: 0,
  },
  {
    value: 'SERVER_BACKEND',
    key: 1,
  },
  {
    value: 'APP_CLIENT',
    key: 2,
  },
  {
    value: 'ETC',
    key: 3,
  },
]

export const getDevelopmentKeyByString = (value: DevelopmentStackType) => {
  if (value === 'WEB_FRONTEND') {
    return 0
  }
  if (value === 'SERVER_BACKEND') {
    return 1
  }
  if (value === 'APP_CLIENT') {
    return 2
  }
  return 3
}
