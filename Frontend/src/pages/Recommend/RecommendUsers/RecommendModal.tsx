import React, { useState } from 'react';
import { Modal, Button, Select, Form } from 'antd';
import { stacks } from 'types/stacks';

const { Option } = Select;

interface RecommendModalProps {
  visible: boolean;
  selectedItems: string[];
  onCancel: () => void;
  onHandlePositionStack: (selectedValues: string[]) => void;
}

const RecommendModal: React.FC<RecommendModalProps> = ({
                                                         visible,
                                                         selectedItems,
                                                         onCancel,
                                                         onHandlePositionStack,
                                                       }) => {
  const [selectedTargets, setSelectedTargets] = useState<string[]>([]);

  const handleSelectChange = (values: string[]) => {
    setSelectedTargets(values);
  };

  const handleOk = () => {
    onHandlePositionStack(selectedTargets);
    onCancel();
  };

  return (
    <Modal
      title="추천 팀원 보기"
      visible={visible}
      onCancel={onCancel}
      onOk={handleOk}
      width={800}
      bodyStyle={{ height: '800px', overflowY: 'auto' }} // 높이를 조절합니다. 필요에 따라 적절한 값을 설정하세요.
    >
      <Form.Item>
        <Select
          mode="multiple"
          placeholder="추천 팀원"
          onChange={handleSelectChange}
          value={selectedTargets}
          style={{ width: '100%' }}
        >
          {selectedItems.map((selectItem) => {
            const stackItem = stacks.find((stack) => stack.value === selectItem);
            return (
              <Option key={stackItem?.value} value={stackItem?.label}>
                {stackItem?.label}
              </Option>
            );
          })}
        </Select>
      </Form.Item>
    </Modal>
  );
};

export default RecommendModal;




