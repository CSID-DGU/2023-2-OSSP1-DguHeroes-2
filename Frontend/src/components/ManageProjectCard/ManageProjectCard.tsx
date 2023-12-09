import { FC } from 'react'
import { useNavigate } from 'react-router-dom'
import { ProjectPositionType, ProjectItemType } from 'types/project'
import { generateRandomProjectCardLogoImg } from 'utils/generateRandomProjectCardLogoImg'
import { getDevelopmentStackColor, translateDevelopmentStack } from 'utils/translateDevelopmentStack'
import { getProjectPositionColor, translateProjectPosition } from 'utils/translateStatus'
import {
  CardMeta,
  DevelopmentStackTag,
  DevelopmentStackTagContainer,
  RepresentativeImg,
  RepresentativeImgBadge,
  RepresentativeImgContainer,
  Root,
} from './styled'

type ManageProjectCardProps = {
  className?: string
  projectItem: ProjectItemType
  position?: ProjectPositionType
}

export const ManageProjectCard: FC<ManageProjectCardProps> = ({ className, projectItem, position }) => {
  const navigate = useNavigate()

  const onClickRoot = () => {
    navigate(`/project/${projectItem.key}`)
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
          {position && (
            <RepresentativeImgBadge color={getProjectPositionColor(position)}>
              {translateProjectPosition(position)}
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
