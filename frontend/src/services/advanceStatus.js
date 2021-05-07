const nextStatus = {
  OPEN: 'IN_PROGRESS',
  IN_PROGRESS: 'DONE',
}

export const advanceStatus = (status) => nextStatus[status]
