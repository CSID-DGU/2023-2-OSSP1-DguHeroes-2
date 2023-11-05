import { FC } from 'react'
import { useNavigate } from 'react-router-dom'
import { ApplyProjectStatusType, ProjectItemType } from 'types/project'
import { generateRandomProjectCardLogoImg } from 'utils/generateRandomProjectCardLogoImg'
import { getDevelopmentStackColor, translateDevelopmentStack } from 'utils/translateDevelopmentStack'
import { getApplyProjectStatusColor, translateApplyProjectStatus } from 'utils/translateStatus'
import {
  CardMeta,
  DevelopmentStackTag,
  DevelopmentStackTagContainer,
  RepresentativeImg,
  RepresentativeImgBadge,
  RepresentativeImgContainer,
  Root,
} from './styled'

type ProjectCardProps = {
  className?: string
  projectItem: ProjectItemType
  isInvited?: boolean
  status?: ApplyProjectStatusType
}

export const ProjectCard: FC<ProjectCardProps> = ({ className, projectItem, isInvited, status }) => {
  const navigate = useNavigate()

  const onClickRoot = () => {
    if(isInvited) {
      navigate(`/project/invite/${projectItem.key}`)
    } else {
      navigate(`/project/${projectItem.key}`)
    }
  }

  return (
    <Root
      className={className}
      onClick={onClickRoot}
      hoverable
      cover={
        <RepresentativeImgContainer>
          <RepresentativeImg
            src={projectItem.representativeImg ?? generateRandomProjectCardLogoImg(projectItem.key)}
            alt={'프로젝트 대표 이미지'}
          />
          {status && (
            <RepresentativeImgBadge color={getApplyProjectStatusColor(status)}>
              {translateApplyProjectStatus(status)}
            </RepresentativeImgBadge>
          )}
        </RepresentativeImgContainer>
      }
    >
      <CardMeta title={projectItem.title} description={`조회수 : ${projectItem.visitedNumber}회`} />
      <DevelopmentStackTagContainer size={[0, 3]} wrap>
        {Object.values(projectItem.requireMemberList.slice(0, 2)).map((requireMemberItemData, index) => (
          <DevelopmentStackTag
            color={getDevelopmentStackColor(requireMemberItemData.developmentStack)}
            key={`development_stack_tag_${index}`}
          >
            {translateDevelopmentStack(requireMemberItemData.developmentStack)}
          </DevelopmentStackTag>
        ))}
      </DevelopmentStackTagContainer>
    </Root>
  )
}
