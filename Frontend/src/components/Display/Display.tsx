import { FC, ReactNode } from 'react'
import { Root } from './styled'

type DisplayProps = {
  className?: string
  on: 'MOBILE' | 'DESKTOP'
  children?: ReactNode
}

export const Display: FC<DisplayProps> = ({ className, on, children }) => {
  return (
    <Root className={className} on={on}>
      {children}
    </Root>
  )
}
