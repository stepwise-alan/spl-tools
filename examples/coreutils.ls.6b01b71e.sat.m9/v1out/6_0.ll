; ModuleID = '/tmp/sea-d0hx9th6/6_0.pp.ms.o.bc'
source_filename = "6_0.c"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@llvm.used = appending global [16 x i8*] [i8* bitcast (void ()* @seahorn.fail to i8*), i8* bitcast (void (i1)* @verifier.assume to i8*), i8* bitcast (void (i1)* @verifier.assume.not to i8*), i8* bitcast (void ()* @verifier.error to i8*), i8* bitcast (void (i1)* @verifier.assume to i8*), i8* bitcast (void (i1)* @verifier.assume.not to i8*), i8* bitcast (void ()* @verifier.error to i8*), i8* bitcast (void ()* @seahorn.fail to i8*), i8* bitcast (void (i1)* @verifier.assume to i8*), i8* bitcast (void (i1)* @verifier.assume.not to i8*), i8* bitcast (void ()* @verifier.error to i8*), i8* bitcast (void ()* @seahorn.fail to i8*), i8* bitcast (void (i1)* @verifier.assume to i8*), i8* bitcast (void (i1)* @verifier.assume.not to i8*), i8* bitcast (void ()* @verifier.error to i8*), i8* bitcast (void ()* @seahorn.fail to i8*)], section "llvm.metadata"

declare i64 @_nd_st_size1() local_unnamed_addr #0

declare i64 @_nd_st_size2() local_unnamed_addr #0

declare i64 @_nd_st_atim_tv_sec1() local_unnamed_addr #0

declare i64 @_nd_st_atim_tv_sec2() local_unnamed_addr #0

declare i64 @_nd_st_ctim_tv_sec1() local_unnamed_addr #0

declare i64 @_nd_st_ctim_tv_sec2() local_unnamed_addr #0

declare i64 @_nd_st_mtim_tv_sec1() local_unnamed_addr #0

declare i64 @_nd_st_mtim_tv_sec2() local_unnamed_addr #0

declare i32 @_nd_st_mode1() local_unnamed_addr #0

declare i32 @_nd_st_mode2() local_unnamed_addr #0

declare i32 @_nd_strcoll12() local_unnamed_addr #0

declare i32 @_nd_strcmp12() local_unnamed_addr #0

declare i32 @_nd_all_fmt() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_size() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_atime() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_ctime() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_mtime() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_dir() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_reverse() local_unnamed_addr #0

declare zeroext i1 @_nd_CONFIG_LOCALE_SUPPORT() local_unnamed_addr #0

declare zeroext i1 @_nd_CONFIG_LFS() local_unnamed_addr #0

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @verifier.assume(i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @verifier.assume.not(i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @seahorn.fail() #1

; Function Attrs: inaccessiblememonly nofree norecurse noreturn nounwind
declare void @verifier.error() #2

; Function Attrs: inaccessiblememonly
declare void @seahorn.fn.enter() local_unnamed_addr #3

declare i32 @verifier.nondet.0()

; Function Attrs: nounwind uwtable
define dso_local i32 @main() local_unnamed_addr #4 {
entry:
  call void @seahorn.fn.enter() #7
  %_0 = call i64 @_nd_st_size1() #7
  %_1 = call i64 @_nd_st_size2() #7
  %_2 = call i64 @_nd_st_atim_tv_sec1() #7
  %_3 = call i64 @_nd_st_atim_tv_sec2() #7
  %_4 = call i64 @_nd_st_ctim_tv_sec1() #7
  %_5 = call i64 @_nd_st_ctim_tv_sec2() #7
  %_6 = call i64 @_nd_st_mtim_tv_sec1() #7
  %_7 = call i64 @_nd_st_mtim_tv_sec2() #7
  %_8 = call i32 @_nd_st_mode1() #7
  %_9 = call i32 @_nd_st_mode2() #7
  %_10 = call i32 @_nd_strcoll12() #7
  %_11 = call i32 @_nd_strcmp12() #7
  %_12 = call i32 @_nd_all_fmt() #7
  %_13 = call zeroext i1 @_nd_sort_size() #7
  %_14 = call zeroext i1 @_nd_sort_atime() #7
  %_15 = call zeroext i1 @_nd_sort_ctime() #7
  %_16 = call zeroext i1 @_nd_sort_mtime() #7
  %_17 = call zeroext i1 @_nd_sort_dir() #7
  %_18 = call zeroext i1 @_nd_sort_reverse() #7
  %_19 = call zeroext i1 @_nd_CONFIG_LOCALE_SUPPORT() #7
  %_20 = call zeroext i1 @_nd_CONFIG_LFS() #7
  %_21 = xor i1 %_19, true
  call void @verifier.assume(i1 %_21) #7
  %_22 = xor i1 %_19, true
  call void @verifier.assume(i1 %_22) #7
  %_23 = xor i1 %_19, true
  call void @verifier.assume(i1 %_23) #7
  %_24 = xor i1 %_19, true
  call void @verifier.assume(i1 %_24) #7
  %_25 = xor i1 %_19, true
  call void @verifier.assume(i1 %_25) #7
  %_26 = xor i1 %_19, true
  call void @verifier.assume(i1 %_26) #7
  %_27 = icmp ne i32 %_10, 2
  %not..i = xor i1 %_14, true
  %or.cond.i = select i1 %not..i, i1 true, i1 %_27
  %or.cond3.i = select i1 %or.cond.i, i1 true, i1 %_18
  %or.cond5.i = select i1 %or.cond3.i, i1 true, i1 %_13
  %_28 = icmp ne i32 %_11, 0
  %or.cond7.i = select i1 %or.cond5.i, i1 true, i1 %_28
  br i1 %or.cond7.i, label %orig.main.exit, label %_29

_29:                                              ; preds = %entry
  %_30 = add nsw i64 %_4, %_5
  %_31 = icmp eq i64 %_30, 2
  br label %orig.main.exit

orig.main.exit:                                   ; preds = %_29, %entry
  %_32 = phi i1 [ false, %entry ], [ %_31, %_29 ]
  call void @verifier.assume(i1 %_32) #7
  %_33 = call i1 @nondet.bool()
  call void @verifier.assume.not(i1 %_33)
  br label %precall

precall:                                          ; preds = %orig.main.exit
  br label %sortcmp

sortcmp:                                          ; preds = %precall
  call void @seahorn.fn.enter() #7
  br i1 %_13, label %_34, label %_36

_34:                                              ; preds = %sortcmp
  %_35 = sub nsw i64 %_1, %_0
  br label %_53

_36:                                              ; preds = %sortcmp
  br i1 %_14, label %_37, label %_39

_37:                                              ; preds = %_36
  %_38 = sub nsw i64 %_3, %_2
  br label %_53

_39:                                              ; preds = %_36
  br i1 %_15, label %_40, label %_42

_40:                                              ; preds = %_39
  %_41 = sub nsw i64 %_5, %_4
  br label %_53

_42:                                              ; preds = %_39
  br i1 %_16, label %_43, label %_45

_43:                                              ; preds = %_42
  %_44 = sub nsw i64 %_7, %_6
  br label %_53

_45:                                              ; preds = %_42
  br i1 %_17, label %_46, label %_53

_46:                                              ; preds = %_45
  %_47 = and i32 %_9, 61440
  %_48 = icmp eq i32 %_47, 16384
  %_49 = zext i1 %_48 to i64
  %_50 = and i32 %_8, 61440
  %_51 = icmp eq i32 %_50, 16384
  %.neg.i = sext i1 %_51 to i64
  %_52 = add nsw i64 %.neg.i, %_49
  br label %_53

_53:                                              ; preds = %_46, %_45, %_43, %_40, %_37, %_34
  %.0.i = phi i64 [ %_35, %_34 ], [ %_38, %_37 ], [ %_41, %_40 ], [ %_44, %_43 ], [ %_52, %_46 ], [ 0, %_45 ]
  %_54 = icmp eq i64 %.0.i, 2
  %spec.select9.v.i = select i1 %_19, i32 %_10, i32 %_11
  %spec.select9.i = sext i32 %spec.select9.v.i to i64
  %.2.i = select i1 %_54, i64 %spec.select9.i, i64 %.0.i
  %.not.i = icmp eq i64 %.2.i, 0
  br i1 %.not.i, label %_62, label %_55

_55:                                              ; preds = %_53
  %_56 = shl i64 %.2.i, 16
  %_57 = ashr i64 %_56, 32
  %.35.i = select i1 %_20, i64 %_57, i64 %.2.i
  %_58 = shl i64 %.35.i, 16
  %_59 = ashr i64 %_58, 32
  %.46.i = select i1 %_20, i64 %_57, i64 %_59
  %_60 = srem i64 %.46.i, 2
  %_61 = add nsw i64 %.46.i, %_60
  br label %_62

_62:                                              ; preds = %_55, %_53
  %.57.i = phi i64 [ %_61, %_55 ], [ %.2.i, %_53 ]
  %.not8.i = icmp eq i64 %.2.i, 2
  br i1 %.not8.i, label %_70, label %_63

_63:                                              ; preds = %_62
  %_64 = shl i64 %.2.i, 16
  %_65 = ashr i64 %_64, 32
  %.3.i = select i1 %_20, i64 %_65, i64 %.2.i
  %_66 = shl i64 %.3.i, 16
  %_67 = ashr i64 %_66, 32
  %.4.i = select i1 %_20, i64 %_65, i64 %_67
  %_68 = srem i64 %.4.i, 2
  %_69 = add nsw i64 %.4.i, %_68
  br label %_70

_70:                                              ; preds = %_63, %_62
  %.5.i = phi i64 [ %_69, %_63 ], [ %.2.i, %_62 ]
  %_71 = trunc i64 %.57.i to i32
  %_72 = sub nsw i32 0, %_71
  %_73 = trunc i64 %.57.i to i32
  %_74 = select i1 %_18, i32 %_72, i32 %_73
  %_75 = trunc i64 %.5.i to i32
  %_76 = sub nsw i32 0, %_75
  %_77 = trunc i64 %.5.i to i32
  %_78 = select i1 %_18, i32 %_76, i32 %_77
  %_79 = icmp eq i32 %_74, %_78
  call void @verifier.assume.not(i1 %_79)
  br label %_80

_80:                                              ; preds = %_70
  br label %verifier.error

verifier.error:                                   ; preds = %_80
  call void @seahorn.fail()
  br label %verifier.error.split

verifier.error.split:                             ; preds = %verifier.error
  ret i32 42
}

declare i1 @nondet.bool()

declare i64 @verifier.nondet.sym.bound()

declare void @shadow.mem.load(i32, i32, i8*)

declare void @shadow.mem.trsfr.load(i32, i32, i8*)

declare i32 @shadow.mem.store(i32, i32, i8*)

declare i32 @shadow.mem.global.init(i32, i32, i8*)

declare i32 @shadow.mem.init(i32, i8*)

declare i32 @shadow.mem.arg.init(i32, i8*)

declare void @shadow.mem.arg.ref(i32, i32, i32, i8*)

declare i32 @shadow.mem.arg.mod(i32, i32, i32, i8*)

declare i32 @shadow.mem.arg.new(i32, i32, i32, i8*)

declare void @shadow.mem.in(i32, i32, i32, i8*)

declare void @shadow.mem.out(i32, i32, i32, i8*)

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @verifier.assert(i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @verifier.assert.not(i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare i1 @sea.is_dereferenceable(i8* nocapture, i64) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @sea.assert.if(i1, i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @sea.synth.assume(i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @sea.synth.assert(i1) #1

; Function Attrs: nofree norecurse nounwind readonly
declare i1 @sea.is_modified(i8* nocapture) #5

; Function Attrs: nofree norecurse nounwind
declare void @sea.reset_modified(i8* nocapture) #6

; Function Attrs: nofree norecurse nounwind readonly
declare i1 @sea.is_read(i8* nocapture) #5

; Function Attrs: nofree norecurse nounwind
declare void @sea.reset_read(i8* nocapture) #6

; Function Attrs: nofree norecurse nounwind readonly
declare i1 @sea.is_alloc(i8* nocapture) #5

; Function Attrs: nofree norecurse nounwind
declare void @sea.tracking_on() #6

; Function Attrs: nofree norecurse nounwind
declare void @sea.tracking_off() #6

; Function Attrs: nofree norecurse nounwind
declare void @sea.free(i8* nocapture) #6

; Function Attrs: nofree norecurse nounwind
declare void @sea.set_shadowmem(i8, i8* nocapture, i8) #6

; Function Attrs: nofree norecurse nounwind readonly
declare i8 @sea.get_shadowmem(i8, i8* nocapture) #5

attributes #0 = { "frame-pointer"="none" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #1 = { inaccessiblememonly nofree norecurse nounwind }
attributes #2 = { inaccessiblememonly nofree norecurse noreturn nounwind }
attributes #3 = { inaccessiblememonly }
attributes #4 = { nounwind uwtable "frame-pointer"="none" "min-legal-vector-width"="0" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #5 = { nofree norecurse nounwind readonly }
attributes #6 = { nofree norecurse nounwind }
attributes #7 = { nounwind }

!llvm.module.flags = !{!0, !1, !2, !3}
!llvm.ident = !{!4}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 7, !"PIC Level", i32 2}
!2 = !{i32 7, !"PIE Level", i32 2}
!3 = !{i32 7, !"uwtable", i32 1}
!4 = !{!"Ubuntu clang version 14.0.0-1ubuntu1"}
