import { useState } from 'react'

type useModalProps = {
  defaultValue?: boolean
}

export const useModal = ({ defaultValue }: useModalProps) => {
  const [open, setOpen] = useState<boolean>(defaultValue ?? false)

  const handleModal = (type: 'OPEN' | 'CLOSE' | 'TOGGLE') => () => {
    if (type === 'OPEN') {
      setOpen(true)
      return
    }
    if (type === 'CLOSE') {
      setOpen(false)
      return
    }
    setOpen((prev) => !prev)
  }

  return { open, handleModal }
}
