<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>开课</el-breadcrumb-item>
      <el-breadcrumb-item>课程</el-breadcrumb-item>
    </el-breadcrumb>
    <!--课程列表主体部分-->
    <el-card>
      <el-row :gutter="25">
        <el-col :span="6">
          <!--搜索区域-->
          <el-input
            placeholder="请输入课程"
            v-model="coursename"
            clearable
            @clear="getCourseList"
          >
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="getCourseList"
            ></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-select
            placeholder="请选择课程类型"
            v-model="coursetype"
            @change="getCourseList"
            clearable
            @clear="getCourseList"
          >
            <el-option label="必修" value="必修课"></el-option>
            <el-option label="选修" value="选修课"></el-option>
          </el-select>
        </el-col>
        <el-col :span="4">
          <el-select
            placeholder="请选择学院"
            v-model="beginacademy"
            @change="getCourseList"
            clearable
            @clear="getCourseList"
          >
            <el-option
              v-for="item in academyList"
              :key="item"
              :label="item"
              :value="item"
            >
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="4">
          <el-select
            placeholder="请选择年级"
            v-model="begingrade"
            @change="getCourseList"
            clearable
            @clear="getCourseList"
          >
            <el-option
              v-for="item in gradeYearList"
              :key="item"
              :label="item"
              :value="item"
            >
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="showCourseDialogVisible"
            >添加课程</el-button
          >
        </el-col>
      </el-row>
      <el-table :data="courseList" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="课程名" prop="coursename"></el-table-column>
        <el-table-column label="课程类型" prop="coursetype"></el-table-column>
        <el-table-column label="总学分" prop="creadit"></el-table-column>
        <el-table-column label="总学时" prop="period"></el-table-column>
        <el-table-column label="预计人数" prop="attendexpect"></el-table-column>
        <el-table-column label="课程人数" prop="atusum"></el-table-column>
        <el-table-column label="操作" width="200px">
          <template scope="scope">
            <el-tooltip
              effect="dark"
              content="修改"
              placement="top-start"
              :enterable="false"
            >
              <el-button
                type="primary"
                icon="el-icon-edit"
                size="mini"
                @click="showEditRoleDialog(scope.row.id)"
              ></el-button>
            </el-tooltip>
            <!-- 删除 -->
            <el-tooltip
              effect="dark"
              content="删除"
              placement="top-start"
              :enterable="false"
            >
              <el-button
                type="danger"
                icon="el-icon-delete"
                size="mini"
                @click="deleteRole(scope.row.id)"
              ></el-button>
            </el-tooltip>
            <!-- 权限 -->
            <el-tooltip
              effect="dark"
              content="分配权限"
              placement="top-start"
              :enterable="false"
            >
              <el-button
                type="warning"
                icon="el-icon-setting"
                size="mini"
                @click="showSetRightDialog(scope.row)"
              ></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <div>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[3, 5, 8, 100]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
        >
        </el-pagination>
      </div>
    </el-card>

    <!--新增课程-->
    <el-dialog
      title="添加用户"
      :visible.sync="addCourseDialogVisible"
      width="50%"
      @close="addCourseDialogClosed"
    >
      <el-form :model="addFrom" ref="addCourseFromRef" :rules="addCourserules" label-width="70px">
        <el-form-item label="课程名" prop="coursename" style="width:250px;" label-width="80px">
          <el-input v-model="addFrom.coursename"> </el-input>
        </el-form-item>
        <el-form-item label="预计人数" prop="attendexpect" style="width:250px;" label-width="80px">
          <el-input v-model="addFrom.attendexpect"> </el-input>
        </el-form-item>
        <el-form-item label="课程类别" prop="coursetype" label-width="80px">
          <el-select v-model="addFrom.coursetype" placeholder="请选择课程类别" >
            <el-option label="必修" value="必修课"></el-option>
            <el-option label="选修" value="选修课"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="行课学期" prop="semester" label-width="80px">
          <el-select v-model="addFrom.semester" placeholder="请选择学期">
            <el-option
              v-for="item in semesterList"
              :key="item.semesterno"
              :label="item.semestername"
              :value="item.semesterno"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="行课周" demo-form-inline required label-width="80px">
          <el-col :span="11">
            <el-form-item prop="beginweek">
              <el-select v-model="addFrom.beginweek" placeholder="请选择起始周">
                <el-option label="第一周" value="01"></el-option>
                <el-option label="第二周" value="02"></el-option>
                <el-option label="第三周" value="03"></el-option>
                <el-option label="第四周" value="04"></el-option>
                <el-option label="第五周" value="05"></el-option>
                <el-option label="第六周" value="06"></el-option>
                <el-option label="第七周" value="07"></el-option>
                <el-option label="第八周" value="08"></el-option>
                <el-option label="第九周" value="09"></el-option>
                <el-option label="第十周" value="10"></el-option>
                <el-option label="第十一周" value="11"></el-option>
                <el-option label="第十二周" value="12"></el-option>
                <el-option label="第十三周" value="13"></el-option>
                <el-option label="第十四周" value="14"></el-option>
                <el-option label="第十五周" value="15"></el-option>
                <el-option label="第十六周" value="16"></el-option>
                <el-option label="第十七周" value="17"></el-option>
                <el-option label="第十八周" value="18"></el-option>
                <el-option label="第十九周" value="19"></el-option>
                <el-option label="第二十周" value="20"></el-option>
                <el-option label="第二十一周" value="21"></el-option>
                <el-option label="第二十二周" value="22"></el-option>
                <el-option label="第二十三周" value="23"></el-option>
                <el-option label="第二十四周" value="24"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col class="line" :span="2">-</el-col>
          <el-col :span="11">
            <el-form-item prop="endweek">
              <el-select v-model="addFrom.endweek" placeholder="请选择结束周">
                <el-option label="第一周" value="01"></el-option>
                <el-option label="第二周" value="02"></el-option>
                <el-option label="第三周" value="03"></el-option>
                <el-option label="第四周" value="04"></el-option>
                <el-option label="第五周" value="05"></el-option>
                <el-option label="第六周" value="06"></el-option>
                <el-option label="第七周" value="07"></el-option>
                <el-option label="第八周" value="08"></el-option>
                <el-option label="第九周" value="09"></el-option>
                <el-option label="第十周" value="10"></el-option>
                <el-option label="第十一周" value="11"></el-option>
                <el-option label="第十二周" value="12"></el-option>
                <el-option label="第十三周" value="13"></el-option>
                <el-option label="第十四周" value="14"></el-option>
                <el-option label="第十五周" value="15"></el-option>
                <el-option label="第十六周" value="16"></el-option>
                <el-option label="第十七周" value="17"></el-option>
                <el-option label="第十八周" value="18"></el-option>
                <el-option label="第十九周" value="19"></el-option>
                <el-option label="第二十周" value="20"></el-option>
                <el-option label="第二十一周" value="21"></el-option>
                <el-option label="第二十二周" value="22"></el-option>
                <el-option label="第二十三周" value="23"></el-option>
                <el-option label="第二十四周" value="24"></el-option>
              </el-select>
            </el-form-item>
            
          </el-col>
        </el-form-item>

        <el-form-item label="行课星期" prop="day" label-width="80px">
          <el-select v-model="addFrom.day" placeholder="请选择星期">
            <el-option label="星期一" value="1"></el-option>
            <el-option label="星期二" value="2"></el-option>
            <el-option label="星期三" value="3"></el-option>
            <el-option label="星期四" value="4"></el-option>
            <el-option label="星期五" value="5"></el-option>
            <el-option label="星期六" value="6"></el-option>
            <el-option label="星期日" value="7"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="行课时间" demo-form-inline label-width="80px" required>
          <el-col :span="11">
            <el-form-item prop="beginclass">
              <el-select v-model="addFrom.beginclass" placeholder="请选择开始时间">
                <el-option label="8点10分" value="1"></el-option>
                <el-option label="10点10分" value="2"></el-option>
                <el-option label="14点10分" value="3"></el-option>
                <el-option label="16点10分" value="4"></el-option>
                <el-option label="19点10分" value="5"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col class="line" :span="2">-</el-col>
          <el-col :span="11">
            <el-form-item prop="endclass">
              <el-select v-model="addFrom.endclass" placeholder="请选择结束时间">
                <el-option label="9点45分" value="1"></el-option>
                <el-option label="11点45分" value="2"></el-option>
                <el-option label="12点35分" value="3"></el-option>
                <el-option label="15点45分" value="4"></el-option>
                <el-option label="17点45分" value="5"></el-option>
                <el-option label="18点35分" value="6"></el-option>
                <el-option label="20点45分" value="7"></el-option>
                <el-option label="21点43分" value="8"></el-option>
              </el-select>
            </el-form-item>
            
          </el-col>
        </el-form-item>

        <el-form-item label="行课地点" prop="spotflag" label-width="80px">
          <el-select v-model="addFrom.spotflag" placeholder="请选择地点">
            <el-option label="第一教学楼a113" value="t1a113"></el-option>
            <el-option label="第一教学楼a111" value="t1a111"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="行课教师" prop="tline" label-width="80px">
          <el-select v-model="addFrom.tline" placeholder="请选择教师">
            <el-option
              v-for="item in teacher"
              :key="item.id"
              :label="item.tname"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="总学分" prop="creadit" label-width="80px">
          <el-select v-model="addFrom.creadit" placeholder="请选择学分">
            <el-option label="1" value="1"></el-option>
            <el-option label="2" value="2"></el-option>
            <el-option label="3" value="3"></el-option>
            <el-option label="4" value="4"></el-option>
            <el-option label="5" value="5"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="总学时" prop="period" label-width="80px">
          <el-select v-model="addFrom.period" placeholder="请选择学时">
            <el-option label="24" value="24"></el-option>
            <el-option label="36" value="36"></el-option>
            <el-option label="48" value="48"></el-option>
            <el-option label="64" value="64"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item
          label="开设学院"
          v-if="addFrom.coursetype === '必修课' ? true : false"
        >
          <el-select v-model="addFrom.beginacademy" placeholder="请选择学院">
            <el-option
              v-for="item in academyList"
              :key="item"
              :label="item"
              :value="item"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item
          label="开设年级"
          v-if="addFrom.coursetype === '必修课' ? true : false"
        >
          <el-select v-model="addFrom.begingrade" placeholder="请选择年级">
            <el-option
              v-for="item in gradeYearList"
              :key="item"
              :label="item"
              :value="item"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item
          label="开设班级"
          v-if="addFrom.coursetype === '必修课' ? true : false"
        >
          <template>
            <el-checkbox
              v-model="classList"
              v-for="item in sclass"
              :key="item"
              :label="item"
            ></el-checkbox>
          </template>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="addCourse">确 定</el-button>
        <el-button @click="addCourseDialogVisible = false">取 消</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
export default {
  created() {
    this.getFormData();
    this.getCourseList();
  },
  data() {
    return {
      addCourseDialogVisible: false,
      addFrom: {
        coursename: "",
        coursetype: "",
        begingrade: "",
        beginacademy: "",
        semester: "",
        execourses: "",
        tline: "",
        timeflag: "",
        spotflag: "",
        attendexpect: "",
        atusum: "",
        creadit: "",
        period: "",
        weeknum: "",
        beginweek: "",
        endweek: "",
        beginclass: "",
        endclass: "",
        day: "",
      },
      gradeYearList: [],
      academyList: [],
      semesterList: [],
      sclass: [],
      teacher: [],
      classList: [],
      //课程列表
      courseList: [],
      pageNum: 1,
      pageSize: 8,
      coursetype: "",
      beginacademy: "",
      begingrade: "",
      coursename:"",
      total: 0,
      addCourserules: {
         coursename: [
            { required: true, message: '请输入活动名称', trigger: 'blur' },
          ],
          attendexpect: [
            { required: true, message: '请输入预计人数', trigger: 'blur' }
          ],
          coursetype: [
            { required: true, message: '请选择课程类别', trigger: 'change' }
          ],
          semester: [
            { required: true, message: '请选择行课学期', trigger: 'change' }
          ],
          beginweek: [
            { required: true, message: '请选择开始周', trigger: 'change' }
          ],
          endweek: [
            { required: true, message: '请选择结束周', trigger: 'change' }
          ],
          day: [
            { required: true, message: '请选择行课星期', trigger: 'change'}
          ],
          beginclass: [
            { required: true, message: '请选择开始时间', trigger: 'change' }
          ],
          endclass: [
            { required: true, message: '请选择结束时间', trigger: 'change' }
          ],
          spotflag: [
            { required: true, message: '请选择行课地点', trigger: 'change' }
          ],
          tline: [
            { required: true, message: '请选择行课教师', trigger: 'change' }
          ],
          creadit: [
            { required: true, message: '请选择课程学分', trigger: 'change' }
          ],
          period: [
            { required: true, message: '请选择课程学时', trigger: 'change' }
          ],
        },
    };
  },
  methods: {
    addCourseDialogClosed() {
      this.$refs.addCourseFromRef.resetFields();
    },
    showCourseDialogVisible() {
      this.addCourseDialogVisible = true;
    },
    async addCourse() {
      this.addFrom.weeknum = this.addFrom.beginweek + "," + this.addFrom.endweek;
      this.addFrom.timeflag =
        this.addFrom.day + "," + this.addFrom.beginclass + "," + this.addFrom.endclass;
      this.addFrom.execourses = this.classList.join(",");
      this.$refs.addCourseFromRef.validate(async valid =>{
        if(!valid){
          return;
        }else{
          const { data: res } = await this.$http.post(
          "course/addCourse",
          this.addFrom
          );
          if (res.state != 200) {
            return this.$message.error("添加课程失败！");
          }
          this.$message.success("添加课程成功！");
          this.addCourseDialogVisible = false;
          this.addCourseDialogClosed();
          this.getCourseList();
        }
      });
      
    },
    handleSizeChange(newSize) {
      this.pageSize = newSize;
      this.getCourseList();
    },
    handleCurrentChange(newNum) {
      this.pageNum = newNum;
      this.getCourseList();
    },
    async getFormData() {
      const { data: res } = await this.$http.get("course/getFormdata");
      this.gradeYearList = res.gradeYearList;
      this.academyList = res.academyList;
      this.semesterList = res.semesterList;
      this.sclass = res.sclass;
      this.teacher = res.teacher;
    },
    async getCourseList() {
      const { data: res } = await this.$http.get("course/getCourseList", {
        params: {
          coursetype: this.coursetype,
          beginacademy: this.beginacademy,
          begingrade: this.begingrade,
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          coursename:this.coursename,
        },
      });

      this.courseList = res.courses;
      this.total = res.number;
    },
  },
};
</script>
<style scoped>

</style>