import { DevelopmentStackType } from 'types/project'

export const defaultDevelopmentStack: DevelopmentStackType[] = ['FRONTEND', 'BACKEND', 'ETC']
export const defaultDevelopmentStackWithKey: {
  value: DevelopmentStackType
  key: number
}[] = [
  {
    value: 'FRONTEND',
    key: 0,
  },
  {
    value: 'BACKEND',
    key: 1,
  },
  {
    value: 'ETC',
    key: 3,
  },
]

export const getDevelopmentKeyByString = (value: DevelopmentStackType) => {
  if (value === 'FRONTEND') {
    return 0
  }
  if (value === 'BACKEND') {
    return 1
  }
  return 3
}
